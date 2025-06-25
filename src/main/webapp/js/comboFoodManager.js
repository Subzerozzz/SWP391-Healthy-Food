// comboProductManager.js sau khi chỉnh thêm console log hỗ trợ debug

document.addEventListener('DOMContentLoaded', function () {

    // Initialize Select2 for all product selects
    initializeSelect2();

    // Handle adding new product
    const addProductBtn = document.querySelector('.add-product');
    console.log('Add Product Button:', addProductBtn);

    if (addProductBtn) {
        addProductBtn.addEventListener('click', function () {
            console.log('Add Product button clicked');
            addNewProductRow();
        });
    } else {
        console.error('Add Product button not found!');
    }

    // Handle product removal
    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('remove-product') || e.target.closest('.remove-product')) {
            console.log('Remove Product clicked');
            const button = e.target.classList.contains('remove-product') ? e.target : e.target.closest('.remove-product');
            if (!button.disabled) {
                removeProductRow(button);
            } else {
                console.warn('Remove button is disabled');
            }
        }
    });

    // Update price when product or quantity changes
    document.addEventListener('change', function (e) {
        if (e.target.classList.contains('product-select') || e.target.classList.contains('product-quantity')) {
            console.log('Product or quantity changed:', e.target.value);
            updateTotalPrice();
        }
    });
});

function initializeSelect2() {

    const selects = $('.product-select');

    // selects.select2({
    //     theme: 'bootstrap4',
    //     placeholder: 'Choose a product',
    //     allowClear: true
    // });
}

function addNewProductRow() {
    console.log('Adding new product row');
    const container = document.querySelector('.product-selection-container');
    console.log('Container:', container);

    if (!container) {
        console.error('No .product-selection-container found!');
        return;
    }

    const firstRow = container.querySelector('.product-selection-row');
    console.log('First row:', firstRow);

    if (!firstRow) {
        console.error('No .product-selection-row found inside container!');
        return;
    }

    const newRow = firstRow.cloneNode(true);
    console.log('Cloned new row:', newRow);

    // Reset values
    const select = newRow.querySelector('.product-select');
    if (!select) {
        console.error('No .product-select found in the new row!');
        return;
    }

    select.value = '';
    select.innerHTML = firstRow.querySelector('.product-select').innerHTML;

    const quantityInput = newRow.querySelector('.product-quantity');
    if (!quantityInput) {
        console.error('No .product-quantity found in the new row!');
        return;
    }

    quantityInput.value = 1;

    const removeButton = newRow.querySelector('.remove-product');
    if (removeButton) {
        removeButton.disabled = false;
    } else {
        console.error('No .remove-product button found in the new row!');
    }

    // Add to container
    container.appendChild(newRow);
    console.log('New row added to container');

    // Reinitialize Select2 for the new select
    console.log('Reinitializing Select2 for the new select');
    $(select).select2({
        theme: 'bootstrap4',
        placeholder: 'Choose a product',
        allowClear: true
    });

    updateTotalPrice();
}

