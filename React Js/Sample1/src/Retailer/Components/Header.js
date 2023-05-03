import React from 'react';

const Header = () =>{
    return(
        <>
            <link rel="icon" type="image/png" sizes="16x16" href="/Assets/Retailer/plugins/images/favicon.png" />
            <link href="/Assets/Retailer/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
            <link href="/Assets/Retailer/plugins/bower_components/bootstrap-extension/css/bootstrap-extension.css" rel="stylesheet" />
            <link href="/Assets/Retailer/plugins/bower_components/morrisjs/morris.css" rel="stylesheet" />
            <link href="/Assets/Retailer/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet" />
            <link href="/Assets/Retailer/css/animate.css" rel="stylesheet" />
            <link href="/Assets/Retailer/css/style.css" rel="stylesheet" />
            <link href="/Assets/Retailer/css/colors/blue.css" id="theme" rel="stylesheet" />

            <nav className="navbar navbar-default navbar-static-top m-b-0">
            <div className="navbar-header">
                <a className="navbar-toggle hidden-sm hidden-md hidden-lg " href="#" data-toggle="collapse" data-target=".navbar-collapse"><i className="ti-menu"></i></a>
                <div className="top-left-part"><a className="logo" href="index.html"><b><img src="/Assets/Retailer/plugins/images/eliteadmin-logo.png" alt="home" /></b><span className="hidden-xs"><strong>Retailer</strong>Panel</span></a></div>
               <ul className="nav navbar-top-links navbar-left hidden-xs">
                    <li>
                        <a href="#" className="open-close hidden-xs waves-effect waves-light"><i className="icon-arrow-left-circle ti-menu"></i></a>
                    </li>
                </ul>
                <ul className="nav navbar-top-links navbar-right pull-right">
                    <li className="dropdown">
                        <a className="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="/Assets/Retailer/plugins/images/users/1.jpg" alt="user-img" width="36" className="img-circle" /><b className="hidden-xs">Steave</b> </a>
                        <ul className="dropdown-menu dropdown-user animated flipInY">
                            <li><a href="#"><i className="ti-user"></i>  My Profile</a></li>
                            <li><a href="#"><i className="ti-email"></i>  Inbox</a></li>
                            <li><a href="#"><i className="ti-settings"></i>  Account Setting</a></li>
                            <li><a href="login.html"><i className="fa fa-power-off"></i>  Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div className="navbar-default sidebar" role="navigation">
            <div className="sidebar-nav navbar-collapse slimscrollsidebar">
            <ul className="nav" id="side-menu">
                    <li className="user-pro">
                        <a href="void(0);" className="waves-effect"><img src="/Assets/Retailer/plugins/images/users/d1.jpg" alt="user-img" className="img-circle"/> <span className="hide-menu">Steve Gection<span className="fa arrow"></span></span>
                        </a>
                        <ul className="nav nav-second-level">
                            <li><a href="#"><i className="ti-user"></i> My Profile</a></li>
                            <li><a href="#"><i className="ti-email"></i> Inbox</a></li>
                            <li><a href="#"><i className="ti-settings"></i> Account Setting</a></li>
                            <li><a href="#"><i className="fa fa-power-off"></i> Logout</a></li>
                        </ul>
                    </li>
                    <li className="nav-small-cap m-t-10">--- Main Menu</li>
                    <li> <a href="index.html" className="waves-effect"><i className="ti-dashboard p-r-10"></i> <span className="hide-menu">Dashboard</span></a> </li>
                    <li> <a href="void(0);" className="waves-effect"><i className="icon-envelope p-r-10"></i> <span className="hide-menu"> Mailbox <span className="fa arrow"></span><span className="label label-rouded label-danger pull-right">6</span></span></a>
                        <ul className="nav nav-second-level">
                            <li> <a href="inbox.html">Inbox</a></li>
                            <li> <a href="inbox-detail.html">Inbox detail</a></li>
                            <li> <a href="compose.html">Compose mail</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        </>
    );
}

export default Header;