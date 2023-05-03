import React,{Suspense,lazy} from 'react';
import RetailerRoute from './Components/RetailerRoute';
const Header = lazy(() => import ('./Components/Header'));
const Footer = lazy(()=> import ('./Components/Footer'));


const RetailerLayout = () =>{
    return(
        <>
            <div class="preloader">
                <div class="cssload-speeding-wheel"></div>
            </div>
            <div id="wrapper">
            
            <Suspense fallback= {<div>Please wait....</div>} >
                <Header />
            </Suspense>
                <div id="page-wrapper">
                    <div class="container-fluid">
                        <div class="row bg-title">
                                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                                    <h4 class="page-title">Retailer Dashboard</h4> 
                                </div>
                                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">  
                                    <ol class="breadcrumb pull-right">
                                        <li><a href="#">Real Estate</a></li>
                                        <li class="active">Dashboard</li>
                                    </ol>
                                </div>
                            </div>
                            <RetailerRoute />
                        
                    </div>
                </div>
                <footer class="footer text-center"> {new Date().getFullYear()} &copy; Retailer Panel by RetailShop </footer>
            </div>
            <Suspense fallback= {<div>Please wait....</div>} >
                <Footer />
            </Suspense>
            
            
        </>
    );

}



export default RetailerLayout;