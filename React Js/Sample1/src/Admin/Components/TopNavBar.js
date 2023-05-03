import React from 'react';

const TopNavBar = () => {
    return <>
        <header className="topbar">
            <nav className="navbar top-navbar navbar-expand-md navbar-light">
                <div className="navbar-header">
                    <a className="navbar-brand" href="index.html">
                        <b>
                            <img src="../assets/images/logo-icon.png" alt="homepage" className="dark-logo" />
                            <img src="../assets/images/logo-light-icon.png" alt="homepage" className="light-logo" />
                        </b>
                        <span>
                            <img src="../assets/images/logo-text.png" alt="homepage" className="dark-logo" />
                            <img src="../assets/images/logo-light-text.png" className="light-logo" alt="homepage" />
                        </span>
                    </a>
                </div>

                <div className="navbar-collapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <a className="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="#">
                                <i className="ti-menu"></i>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link sidebartoggler hidden-sm-down waves-effect waves-dark" href="#">
                                <i className="ti-menu"></i>
                            </a>
                        </li>
                        <li className="nav-item hidden-sm-down"></li>
                    </ul>
                    <ul className="navbar-nav my-lg-0">
                        <li className="nav-item hidden-xs-down search-box">
                            <a className="nav-link hidden-sm-down waves-effect waves-dark" href="#">
                                <i className="ti-search"></i>
                            </a>
                            <form className="app-search">
                                <input type="text" className="form-control" placeholder="Search & enter" />
                                <a className="srh-btn"><i className="ti-close"></i></a>
                            </form>
                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle waves-effect waves-dark" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="../assets/images/users/1.jpg" alt="user" className="profile-pic" />
                            </a>
                            <div className="dropdown-menu dropdown-menu-right animated flipInY">
                                <ul className="dropdown-user">
                                    <li>
                                        <div className="dw-user-box">
                                            <div className="u-img"><img src="../assets/images/users/1.jpg" alt="user" /></div>
                                            <div className="u-text">
                                                <h4>Steave Jobs</h4>
                                                <p className="text-muted">varun@gmail.com</p>
                                                <a href="pages-profile.html" className="btn btn-rounded btn-danger btn-sm">View Profile</a>
                                            </div>
                                        </div>
                                    </li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="#"><i className="ti-user"></i> My Profile</a></li>
                                    <li><a href="#"><i className="ti-wallet"></i> My Balance</a></li>
                                    <li><a href="#"><i className="ti-email"></i> Inbox</a></li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="#"><i className="ti-settings"></i> Account Setting</a></li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="#"><i className="fa fa-power-off"></i> Logout</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    </>;
}

export default TopNavBar;