import React from 'react';
import Header from './Components/Header';
import Footer from './Components/Footer';
import CustomerRoutes from './Components/CustomerRoutes';
import {Helmet} from 'react-helmet';

const CustomerLayout = () => {
    return(
        <>
            <Header />
            <CustomerRoutes />
            <Footer />
        </>
    );
}

export default CustomerLayout;