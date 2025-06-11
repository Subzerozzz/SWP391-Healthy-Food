<%-- 
    Document   : header
    Created on : May 24, 2025, 8:28:09 PM
    Author     : Dell
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main-nav nav-three">
                <div class="container">
                    <nav class="navbar navbar-expand-md navbar-light ">
                        <a class="navbar-brand" href="index.html">
                            <img src="${pageContext.request.contextPath}/images/logos/logo-2.png" class="logo-one" alt="Logo">
                            <img src="${pageContext.request.contextPath}/images/logos/logo-1.png" class="logo-two" alt="Logo">
                        </a>

                        <div class="collapse navbar-collapse mean-menu" id="navbarSupportedContent">
                            <ul class="navbar-nav m-auto">
                                <li class="nav-item">
                                    <a href="#" class="nav-link active">
                                        Home 
                                        <i class='bx bx-chevron-down'></i>
                                    </a>
                                    
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        Pages 
                                        <i class='bx bx-chevron-down'></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item">
                                            <a href="about.html" class="nav-link">
                                                About
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="#" class="nav-link">
                                                Shop 
                                                <i class='bx bx-chevron-down'></i>
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li class="nav-item">
                                                    <a href="shop-details.html" class="nav-link">
                                                        Shop Details
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="shop-details-left-sidebar.html" class="nav-link">
                                                        Shop Details Left Sidebar
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="shop-details-right-sidebar.html" class="nav-link">
                                                        Shop Details Right Sidebar 
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="tracking-order.html" class="nav-link">
                                                        Tracking Order 
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="customer-services.html" class="nav-link">
                                                        Customer Services  
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="compare.html" class="nav-link">
                                                        Compare  
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="cart.html" class="nav-link">
                                                        Cart  
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="checkout.html" class="nav-link">
                                                        Checkout  
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="wishlist.html" class="nav-link">
                                                        Wishlist  
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="my-account.html" class="nav-link">
                                                        My Account  
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        
                                        <li class="nav-item">
                                            <a href="faq.html" class="nav-link">
                                                FAQ
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="team.html" class="nav-link">
                                                Team
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="testimonials.html" class="nav-link">
                                                Testimonials
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="#" class="nav-link">
                                                User
                                                <i class='bx bx-chevron-down'></i>
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li class="nav-item">
                                                    <a href="log-in.html" class="nav-link">
                                                        Log In
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="register.html" class="nav-link">
                                                        Registration
                                                    </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="forget-password.html" class="nav-link">
                                                        Forget Password
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="nav-item">
                                            <a href="terms-condition.html" class="nav-link">
                                                Terms & Conditions
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="privacy-policy.html" class="nav-link">
                                                Privacy Policy
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="404.html" class="nav-link">
                                                404 page
                                            </a>
                                        </li>

                                        <li class="nav-item">
                                            <a href="search-page.html" class="nav-link">
                                                Search Page
                                            </a>
                                        </li>
                                        
                                        <li class="nav-item">
                                            <a href="coming-soon.html" class="nav-link">
                                                Coming Soon
                                            </a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="nav-item">
                                    <a href="${pageContext.request.contextPath}/shop" class="nav-link" style="margin-right: 30px">
                                        Shop
                                    </a>
                                </li>

                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        Blog
                                        <i class='bx bx-chevron-down'></i>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li class="nav-item">
                                            <a href="blog-1.html" class="nav-link">
                                                Blog Style One 
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="blog-2.html" class="nav-link">
                                                Blog Style Two 
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="blog-details.html" class="nav-link">
                                                Blog Details 
                                            </a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="nav-item">
                                    <a href="contact.html" class="nav-link">
                                        Contact
                                    </a>
                                </li>
                            </ul>

                            <div class="nav-bar-side-2">
                                <div class="language-nav-list">
                                    <select class="language-list-item">
                                        <option>English</option>
                                        <option>العربيّة</option>
                                        <option>Deutsch</option>
                                        <option>Português</option>
                                        <option>简体中文</option>
                                    </select>	
                                </div>
                                <div class="side-nav-cart">
                                    <a href="#"><i class='bx bx-cart'></i></a>
                                    <span>1</span>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
    </body>
</html>
