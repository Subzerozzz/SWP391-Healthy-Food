document.addEventListener('DOMContentLoaded', function () {
    // Initialize the form
    initComboForm();
    // Initialize the first food row
    initFirstFoodRow();

    // Set up event listeners
    setupEventListeners();
    updateHiddenInputs();
});

function initComboForm() {
    const comboForm = document.getElementById('comboForm');
    if (comboForm) {
        comboForm.addEventListener('submit', function (e) {
            // Validate combo name
            const nameInput = document.getElementById('name');
            const nameValue = nameInput.value.trim();
            const nameRegex = /^[a-zA-Z0-9\sÀ-ỹà-ỹ_.,-]+$/;

            if (!nameRegex.test(nameValue)) {
                alert('Combo name cannot contain special characters!');
                nameInput.focus();
                e.preventDefault();
                return false;
            }

            // Validate at least one food is selected
            if (!validateAtLeastOneFood()) {
                alert('Please select at least one food item for the combo');
                e.preventDefault();
                return false;
            }

            // Validate discount price
            if (!validateDiscountPrice()) {
                e.preventDefault();
                return false;
            }

            // Update hidden inputs before submission
            updateHiddenInputs();
        });
    }

    // Initialize discount price validation
    const discountPriceInput = document.getElementById('discountPrice');
    if (discountPriceInput) {
        discountPriceInput.addEventListener('change', updateSavings);
        discountPriceInput.addEventListener('input', updateSavings);
    }
}

function initFirstFoodRow() {
    const firstRow = document.querySelector('.food-selection-row');
    if (firstRow) {
        const removeBtn = firstRow.querySelector('.remove-food');
        if (removeBtn)
            removeBtn.disabled = true;
    }
}

function setupEventListeners() {
    // Add food button
    const addFoodBtn = document.querySelector('.add-food');
    if (addFoodBtn) {
        addFoodBtn.addEventListener('click', addNewFoodRow);
    }

    // Delegated event listeners for dynamic elements
    document.addEventListener('click', function (e) {
        // Remove food button
        if (e.target.closest('.remove-food')) {
            const button = e.target.closest('.remove-food');
            if (!button.disabled) {
                removeFoodRow(button);
            }
        }
    });

    // Handle changes in food selection or quantity
    document.addEventListener('change', function (e) {
        if (e.target.classList.contains('food-select') ||
                e.target.classList.contains('food-quantity')) {
            updateTotalPrice();
        }
    });
}

function addNewFoodRow() {
    const container = document.querySelector('.food-selection-container');
    if (!container)
        return;

    // Get the first row as template
    const firstRow = container.querySelector('.food-selection-row');
    if (!firstRow)
        return;

    // Clone the row
    const newRow = firstRow.cloneNode(true);

    // Reset values
    const select = newRow.querySelector('.food-select');
    const quantityInput = newRow.querySelector('.food-quantity');
    const removeButton = newRow.querySelector('.remove-food');

    if (select)
        select.value = '';
    if (quantityInput)
        quantityInput.value = 1;
    if (removeButton)
        removeButton.disabled = false;

    // Add to container
    container.appendChild(newRow);

    // Initialize Select2 if needed
    if (select && typeof $.fn.select2 === 'function') {
        $(select).select2({
            theme: 'bootstrap4',
            placeholder: 'Choose a food',
            allowClear: true
        });
    }

    // Enable remove buttons if needed
    updateRemoveButtons();
}

function removeFoodRow(button) {
    const row = button.closest('.food-selection-row');
    if (row) {
        row.remove();
        updateTotalPrice();
        updateRemoveButtons();
    }
}

function updateTotalPrice() {
    let totalPrice = 0;
    const rows = document.querySelectorAll('.food-selection-row');

    rows.forEach(row => {
        const select = row.querySelector('.food-select');
        const quantityInput = row.querySelector('.food-quantity');

        if (select && select.value && quantityInput && quantityInput.value) {
            const selectedOption = select.options[select.selectedIndex];
            const price = parseFloat(selectedOption.dataset.price) || 0;
            const quantity = parseInt(quantityInput.value) || 1; // Mặc định là 1 nếu không nhập

            totalPrice += price * quantity;
        }
    });

    // Cập nhật hiển thị giá và input ẩn
    const priceDisplay = document.getElementById('original-price-display');
    const priceInput = document.getElementById('originalPrice'); // Chú ý tên ID phải khớp với form

    if (priceDisplay) {
        priceDisplay.textContent = totalPrice.toLocaleString('vi-VN');
    }

    if (priceInput) {
        priceInput.value = totalPrice;
    }

    // Cập nhật giá trị tối đa cho discount và savings
    const discountInput = document.getElementById('discountPrice');
    if (discountInput) {
        discountInput.max = totalPrice;

        // Tự động điều chỉnh nếu giá discount vượt quá giá mới
        const currentDiscount = parseFloat(discountInput.value) || 0;
        if (currentDiscount > totalPrice) {
            discountInput.value = totalPrice;
        }
    }

    // Luôn cập nhật savings khi giá thay đổi
    updateSavings();
}

function updateSavings() {
    const originalPrice = parseFloat(document.getElementById('originalPrice')?.value) || 0;
    const discountPrice = parseFloat(document.getElementById('discountPrice')?.value) || 0;
    const savings = Math.max(0, originalPrice - discountPrice); // Đảm bảo không âm

    const savingsDisplay = document.getElementById('savings-display');
    if (savingsDisplay) {
        savingsDisplay.textContent = savings.toLocaleString('vi-VN');
    }
}

// Thêm sự kiện cho các input
document.querySelectorAll('.food-select, .food-quantity').forEach(input => {
    input.addEventListener('change', updateTotalPrice);
});

document.getElementById('discountPrice')?.addEventListener('input', function () {
    updateSavings();
});

// Gọi lần đầu khi trang tải
document.addEventListener('DOMContentLoaded', function () {
    updateTotalPrice();
});

function validateAtLeastOneFood() {
    const rows = document.querySelectorAll('.food-selection-row');
    for (let row of rows) {
        const select = row.querySelector('.food-select');
        if (select && select.value) {
            return true;
        }
    }
    return false;
}

function validateDiscountPrice() {
    const originalPrice = parseFloat(document.getElementById('originalPrice').value) || 0;
    const discountPrice = parseFloat(document.getElementById('discountPrice').value) || 0;

    if (discountPrice <= 0) {
        alert('Discount price must be greater than 0');
        return false;
    }

    if (discountPrice >= originalPrice) {
        alert('Discount price must be less than original price');
        return false;
    }

    return true;
}

function updateHiddenInputs() {
    const foodIds = [];
    const quantities = [];

    document.querySelectorAll('.food-selection-row').forEach(row => {
        const select = row.querySelector('.food-select');
        const quantityInput = row.querySelector('.food-quantity');

        if (select && select.value && quantityInput) {
            foodIds.push(select.value);
            quantities.push(quantityInput.value);
        }
    });

    document.getElementById('foodIdsInput').value = foodIds.join(',');
    document.getElementById('quantitiesInput').value = quantities.join(',');
}

function updateRemoveButtons() {
    const rows = document.querySelectorAll('.food-selection-row');
    rows.forEach((row, index) => {
        const removeBtn = row.querySelector('.remove-food');
        if (removeBtn) {
            removeBtn.disabled = rows.length <= 1;
        }
    });
}