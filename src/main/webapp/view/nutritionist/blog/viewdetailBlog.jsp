<%-- 
    Document   : addBlog
    Created on : May 30, 2025, 3:32:10 PM
    Author     : Predator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<!--<![endif]-->

<style>
                                            .image-frame {
                                                position: relative;
                                                width: 320px;
                                                height: 240px;
                                                border-radius: 24px;
                                                overflow: hidden;
                                                background: linear-gradient(145deg, #f0f4f8, #e2e8f0);
                                                box-shadow:
                                                    0 8px 32px rgba(0, 0, 0, 0.12),
                                                    0 2px 8px rgba(0, 0, 0, 0.08),
                                                    inset 0 1px 0 rgba(255, 255, 255, 0.2);
                                                transition: all 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
                                                margin: 24px auto;
                                                cursor: pointer;
                                                backdrop-filter: blur(10px);
                                            }

                                            .image-frame::before {
                                                content: '';
                                                position: absolute;
                                                top: 0;
                                                left: 0;
                                                right: 0;
                                                bottom: 0;
                                                background: linear-gradient(135deg,
                                                    rgba(255, 255, 255, 0.1) 0%,
                                                    rgba(255, 255, 255, 0.05) 50%,
                                                    rgba(0, 0, 0, 0.02) 100%);
                                                z-index: 2;
                                                pointer-events: none;
                                                transition: opacity 0.3s ease;
                                            }

                                            .image-frame img {
                                                width: 100%;
                                                height: 100%;
                                                object-fit: cover;
                                                display: block;
                                                transition: all 0.5s cubic-bezier(0.175, 0.885, 0.32, 1.275);
                                                filter: brightness(1) contrast(1.05) saturate(1.1);
                                            }

                                            .image-frame:hover {
                                                transform: translateY(-8px) scale(1.02);
                                                box-shadow:
                                                    0 20px 60px rgba(0, 0, 0, 0.15),
                                                    0 8px 32px rgba(0, 0, 0, 0.1),
                                                    inset 0 1px 0 rgba(255, 255, 255, 0.3);
                                            }

                                            .image-frame:hover::before {
                                                opacity: 0.7;
                                            }

                                            .image-frame:hover img {
                                                transform: scale(1.08);
                                                filter: brightness(1.1) contrast(1.1) saturate(1.2);
                                            }

                                            .image-frame:active {
                                                transform: translateY(-4px) scale(1.01);
                                                transition: all 0.15s ease;
                                            }

                                            /* Responsive design */
                                            @media (max-width: 768px) {
                                                .image-frame {
                                                    width: 90%;
                                                    max-width: 300px;
                                                    height: 200px;
                                                    margin: 16px auto;
                                                }
                                            }

                                            /* Dark theme variant */
                                            .image-frame.dark {
                                                background: linear-gradient(145deg, #1a202c, #2d3748);
                                                box-shadow:
                                                    0 8px 32px rgba(0, 0, 0, 0.3),
                                                    0 2px 8px rgba(0, 0, 0, 0.2),
                                                    inset 0 1px 0 rgba(255, 255, 255, 0.1);
                                            }

                                            .image-frame.dark::before {
                                                background: linear-gradient(135deg,
                                                    rgba(255, 255, 255, 0.05) 0%,
                                                    rgba(255, 255, 255, 0.02) 50%,
                                                    rgba(0, 0, 0, 0.1) 100%);
                                            }

                                            /* Glass morphism variant */
                                            .image-frame.glass {
                                                background: rgba(255, 255, 255, 0.1);
                                                backdrop-filter: blur(20px);
                                                border: 1px solid rgba(255, 255, 255, 0.2);
                                                box-shadow:
                                                    0 8px 32px rgba(0, 0, 0, 0.1),
                                                    inset 0 1px 0 rgba(255, 255, 255, 0.3);
                                            }

                                            /* Neon glow variant */
                                            .image-frame.neon {
                                                box-shadow:
                                                    0 0 20px rgba(59, 130, 246, 0.3),
                                                    0 0 40px rgba(59, 130, 246, 0.1),
                                                    0 8px 32px rgba(0, 0, 0, 0.12);
                                                border: 2px solid rgba(59, 130, 246, 0.3);
                                            }

                                            .image-frame.neon:hover {
                                                box-shadow:
                                                    0 0 30px rgba(59, 130, 246, 0.5),
                                                    0 0 60px rgba(59, 130, 246, 0.2),
                                                    0 20px 60px rgba(0, 0, 0, 0.15);
                                                border-color: rgba(59, 130, 246, 0.6);
                                            }
</style>
<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:33 GMT -->
<head>
    <!-- Basic Page Needs -->
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <title>Remos eCommerce Admin Dashboard HTML Template</title>

    <meta name="author" content="themesflat.com">

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- Theme Style -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.min_1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animation.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_1.css">



    <!-- Font -->
    <link rel="stylesheet" href="font/fonts.css">

    <!-- Icon -->
    <link rel="stylesheet" href="icon/style.css">

    <!-- Favicon and Touch Icons  -->
    <link rel="shortcut icon" href="images/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="images/favicon.png">

</head>

<body class="body">

    <!-- #wrapper -->
    <div id="wrapper">
        <!-- #page -->
        <div id="page" class="">
            <!-- layout-wrap -->
           <div class="layout-wrap">
                <!-- preload -->
                <div id="preload" class="preload-container">
                    <div class="preloading">
                        <span></span>
                    </div>
                </div>
                <!-- /preload -->
                <!-- section-menu-left -->
                <jsp:include page="../../common/nutritionist/sidebar.jsp"></jsp:include>
                <!-- /section-menu-left -->
                <!-- section-content-right -->
                <div class="section-content-right">
                    <!-- header-dashboard -->
                    <jsp:include page="../../common/nutritionist/headerdashboard.jsp"></jsp:include> 
                    <!-- /header-dashboard -->
                    <!-- main-content -->
                    <div class="main-content">
                        <!-- main-content-wrap -->
                        <div class="main-content-inner">
                            <!-- main-content-wrap -->
                            <div class="main-content-wrap">
                                <div class="flex items-center flex-wrap justify-between gap20 mb-27">
                                    <h3>Detail Blog</h3>
                                    <ul class="breadcrumbs flex items-center flex-wrap justify-start gap10">
                                        <li>
                                            <a href="index"><div class="text-tiny">Dashboard</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                        <li>
                                            <a href="#"><div class="text-tiny">Ecommerce</div></a>
                                        </li>
                                        <li>
                                            <i class="icon-chevron-right"></i>
                                        </li>
                                    </ul>
                                </div>
                                <!-- form-add-product -->
                                <form class="tf-section-2 form-add-product" >
                                    <div class="wg-box">
                                        <fieldset class="title">
                                            <div class="body-title mb-10">Blog Title: <span class="tf-color-1">*</span></div>
                                            <div class="col" style="color: red; font-size: 24px; font-weight: bold;">${blog.title}</div>
                                        </fieldset>
                                        <div class="gap22 cols">
                                            <fieldset class="author">
                                                <div class="body-title mb-10">Author:<span class="tf-color-1">*</span></div>
                                                <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.author}</div>
                                            </fieldset>
                                            <fieldset class="briefinfo">
                                               <div class="body-title mb-10">Brief_info: <span class="tf-color-1">*</span></div>
                                            <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.brief_info}</div>
                                            </fieldset>
                                        </div>
                                            <div class="body-title mb-10">
                                                Description: <span class="tf-color-1">*</span>
                                            </div>
                                            <div class="col" style="color: black; font-size: 20px; font-weight: bold; white-space: normal; overflow: visible; text-overflow: unset;">
                                                ${blog.content}
                                            </div>
                                            </fieldset>
                                    </div>              
                                    <div class="wg-box">
                                        
                                        <<div class="image-frame">
                                            <img src="${blog.thumbnailblogs}" alt="Blog thumbnail">
                                        </div>
                                            <fieldset class="name">
                                                <div class="body-title mb-10">Blog date</div>
                                                <div class="select">
                                                    <div class="col" style="color: black; font-size: 20px; font-weight: bold;">${blog.birth_date}</div>
                                                </div>
                                            </fieldset>
                                    </div>
                                                <a href="${pageContext.request.contextPath}/manage-blog">
                                                    <button>Back</button>
                                                </a> 
                                </form>
                                <!-- /form-add-product -->
                            </div>
                            <!-- /main-content-wrap -->
                        </div>
                        <!-- /main-content-wrap -->
                        <!-- bottom-page -->
                        <div class="bottom-page">
                            
                        </div>
                        <!-- /bottom-page -->
                    </div>
                    <!-- /main-content -->
                </div>
                <!-- /section-content-right -->
            </div>
            <!-- /layout-wrap -->
        </div>
        <!-- /#page -->
    </div>
    <!-- /#wrapper -->

    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/jquery.min_1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/zoom.js"></script>
    <script src="${pageContext.request.contextPath}/js/switcher.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme-settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>


<!-- Mirrored from themesflat.co/html/remos/add-product.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 26 May 2025 09:44:35 GMT -->
</html>
