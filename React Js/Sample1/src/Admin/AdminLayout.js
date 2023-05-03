import React from 'react';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';
import AdminRoutes from './Components/AdminRoutes';
import Perloader from './Components/Perloader';
import { Helmet } from 'react-helmet';

const AdminLayout = () => {
    return (<div className="fix-header card-no-border fix-sidebar">
        <link href="/Assets/Admin/css/bootstrap.min.css" rel="stylesheet" />
        <link href="/Assets/Admin/css/style.css" rel="stylesheet" />
        <link href="/Assets/Admin/css/colors/default-dark.css" id="theme" rel="stylesheet" />
        <link href="/Assets/Admin/css/spinners.css" rel="stylesheet" />
        <link href="/Assets/Admin/icons/themify-icons/themify-icons.css" rel="stylesheet" />
        <link href="/Assets/Admin/icons/material-design-iconic-font/css/materialdesignicons.min.css" rel="stylesheet" />
        <link href="/Assets/Admin/icons/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        {/* <Perloader /> */}
        <div id="main-wrapper">
            <TopNavBar />
            <LeftNavBar />
            <div className="page-wrapper">
                <div className="container-fluid">
                    <AdminRoutes />
                </div>
                <Footer />
            </div>
        </div>

        <Helmet>
            <script src="/Assets/Admin/js/jquery.min.js" />
            <script src="/Assets/Admin/js/popper.min.js" />
            <script src="/Assets/Admin/js/bootstrap.min.js" />
            <script type="text/javascript" src="/Assets/Admin/js/perfect-scrollbar.jquery.min.js" />
            <script type="text/javascript" src="/Assets/Admin/js/waves.js" />
            <script type="text/javascript" src="/Assets/Admin/js/sidebarmenu.js" />
            <script type="text/javascript" src="/Assets/Admin/js/sticky-kit.min.js" />
            <script type="text/javascript" src="/Assets/Admin/js/jquery.sparkline.min.js" />
            <script type="text/javascript" src="/Assets/Admin/js/custom.min.js" />
            <script type="text/javascript" src="/Assets/Admin/js/jasny-bootstrap.js" />
            <script type="text/javascript" src="/Assets/Admin/js/jQuery.style.switcher.js" />
        </Helmet>
    </div>);
}

export default AdminLayout;