import React, { useState } from 'react';
import { Helmet } from 'react-helmet';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';

const ProductImage = (props) => {

    const [getProductImage, setProductImage] = useState({});
    const [getProduct, setProduct] = useState({});

    useEffect(() => {
        const GetProduct = async () => {
            
            const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/GetById/" + props.Id);
            const data = await response.json();

            setProduct(data);
        };

        const GetProductImage = () => {
            const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/ProductImage/GetByProduct/" + props.Id);
            const data = await response.json();

            setProductImage(data);
        }

        GetProduct();
        GetProductImage();
    }, []);

    return (
        <div className="fix-header card-no-border fix-sidebar">
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
                {/* <AdminRoutes /> */}
                <div className="page-wrapper">
                    <div className="container-fluid">
                        <PageTitle Title="Product" AddPageURL="ProductAdd" />
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="table-responsive">
                                            <table className="table full-color-table full-info-table hover-table color-bordered-table info-bordered-table">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Category</th>
                                                        <th>Name</th>
                                                        <th>Price</th>
                                                        <th>Description</th>
                                                        <th>Active</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    {
                                                        Object.keys(getProduct).map(index => {
                                                            return <tr key={index}>
                                                                <td>{getProduct[index].id}</td>
                                                                <td>{getProduct[index].categoryId.name}</td>
                                                                <td>{getProduct[index].name}</td>
                                                                <td>{getProduct[index].price}</td>
                                                                <td>{getProduct[index].description}</td>
                                                                <td>{getProduct[index].isActive.toString()}</td>
                                                                <td className="text-nowrap">
                                                                    <a href="#" data-toggle="tooltip" className="fa fa-pencil-square-o text-inverse m-r-10" data-original-title="Edit"></a>
                                                                    <a href="#" data-toggle="tooltip" className="fa fa-trash text-danger" data-original-title="Close"></a>
                                                                </td>
                                                            </tr>
                                                        })
                                                    }
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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

export default ProductImage;