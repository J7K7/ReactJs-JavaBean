import React from 'react';

const Header = () =>{
    return(
        <>
            <link href="/Assets/Customer/css/bootstrap.min.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/font-awesome.min.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/prettyPhoto.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/price-range.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/animate.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/main.css" rel="stylesheet" />
            <link href="/Assets/Customer/css/responsive.css" rel="stylesheet" />
            <link rel="shortcut icon" href="/Assets/Customer/images/ico/favicon.ico" />
            <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/Assets/Customer/images/ico/apple-touch-icon-144-precomposed.png" />
            <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/Assets/Customer/images/ico/apple-touch-icon-114-precomposed.png" />
            <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/Assets/Customer/images/ico/apple-touch-icon-72-precomposed.png" />
            <link rel="apple-touch-icon-precomposed" href="/Assets/Customer/images/ico/apple-touch-icon-57-precomposed.png" />
            <header id="header">
                <div className="header-middle">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-4 clearfix">
                                <div className="logo pull-left">
                                    <a href="index.html"><img src="/Assets/Customer/images/home/logo.png" alt="" /></a>
                                </div>
                            </div>
                            <div className="col-md-8 clearfix">
                                <div className="shop-menu clearfix pull-right">
                                    <ul className="nav navbar-nav">
                                        <li><a href="/Retailer/Dashboard"><i className="fa fa-user"></i> Account</a></li>
                                        <li><a href="#"><i className="fa fa-star"></i> Wishlist</a></li>
                                        <li><a href="checkout.html"><i className="fa fa-crosshairs"></i> Checkout</a></li>
                                        <li><a href="cart.html"><i className="fa fa-shopping-cart"></i> Cart</a></li>
                                        <li><a href="login.html"><i className="fa fa-lock"></i> Login</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            
                <div className="header-bottom">
                    <div className="container">
                        <div className="row">
                            <div className="col-sm-9">
                                <div className="navbar-header">
                                    <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                        <span className="sr-only">Toggle navigation</span>
                                        <span className="icon-bar"></span>
                                        <span className="icon-bar"></span>
                                        <span className="icon-bar"></span>
                                    </button>
                                </div>
                                <div className="mainmenu pull-left">
                                    <ul className="nav navbar-nav collapse navbar-collapse">
                                        <li><a href="index.html" className="active">Home</a></li>
                                        <li className="dropdown"><a href="#">Shop<i className="fa fa-angle-down"></i></a>
                                            <ul role="menu" className="sub-menu">
                                                <li><a href="shop.html">Products</a></li>
                                                <li><a href="product-details.html">Product Details</a></li> 
                                                <li><a href="checkout.html">Checkout</a></li> 
                                                <li><a href="cart.html">Cart</a></li> 
                                                <li><a href="login.html">Login</a></li> 
                                            </ul>
                                        </li> 
                                        <li className="dropdown"><a href="#">Blog<i className="fa fa-angle-down"></i></a>
                                            <ul role="menu" className="sub-menu">
                                                <li><a href="blog.html">Blog List</a></li>
                                                <li><a href="blog-single.html">Blog Single</a></li>
                                            </ul>
                                        </li> 
                                        <li><a href="404.html">404</a></li>
                                        <li><a href="contact-us.html">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            
        </>
    );
}

export default Header;