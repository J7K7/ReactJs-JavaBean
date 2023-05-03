import React from 'react';
import Helmet from 'react-helmet';
// import ScriptTag from 'react-script-tag';
const Footer = ()=>{
    return (
        <>
            <Helmet>
                <script src="/Assets/Retailer/plugins/bower_components/jquery/dist/jquery.min.js" />
                <script src="/Assets/Retailer/bootstrap/dist/js/tether.min.js" />
                <script src="/Assets/Retailer/bootstrap/dist/js/bootstrap.min.js" />
                <script src="/Assets/Retailer/plugins/bower_components/bootstrap-extension/js/bootstrap-extension.min.js" />
                <script src="/Assets/Retailer/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js" />
                <script src="/Assets/Retailer/js/jquery.slimscroll.js" />
                <script src="/Assets/Retailer/js/waves.js" />
                <script src="/Assets/Retailer/js/custom.min.js" />
                <script src="/Assets/Retailer/plugins/bower_components/raphael/raphael-min.js" />
                <script src="/Assets/Retailer/plugins/bower_components/morrisjs/morris.js" />
                <script src="/Assets/Retailer/plugins/bower_components/jquery-sparkline/jquery.sparkline.min.js" />
                <script src="/Assets/Retailer/plugins/bower_components/jquery-sparkline/jquery.charts-sparkline.js" />
                <script src="/Assets/Retailer/js/real-estate.js" />
                <script src="/Assets/Retailer/plugins/bower_components/styleswitcher/jQuery.style.switcher.js" />
            </Helmet>
        </>
    );
}

export default Footer;