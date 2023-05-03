import React from 'react';
import {Helmet} from 'react-helmet';

const Footer = () =>{
    return(
        <>
            <footer id="footer">
                <div className="footer-bottom">
                    <div className="container">
                        <div className="row">
                            <p className="pull-left">Copyright © {new Date().getFullYear()} RetailShop Inc. All rights reserved.</p>
                            <p className="pull-right">Designed by <span><a target="_blank" href="#">404 NOT FOUND</a></span></p>
                        </div>
                    </div>
                </div>
            </footer>
            <Helmet>
                <script src="/Assets/Customer/js/jquery.js"></script>
                <script src="/Assets/Customer/js/bootstrap.min.js"></script>
                <script src="/Assets/Customer/js/jquery.scrollUp.min.js"></script>
                <script src="/Assets/Customer/js/price-range.js"></script>
                <script src="/Assets/Customer/js/jquery.prettyPhoto.js"></script>
                <script src="/Assets/Customer/js/main.js"></script>
            </Helmet>
        </>
    );
}

export default Footer;