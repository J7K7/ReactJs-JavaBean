import React, { useEffect, useState } from 'react';
import Helmet from 'react-helmet';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';
import AdminRoutes from './Components/AdminRoutes';

const Product = () => {

    const [getProduct, setProduct] = useState({});

    useEffect(() => {
        const GetProduct = async () => {

            const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/Get");
            const data = await response.json();

            setProduct(data);
        };

        GetProduct();
    }, []);

    return (
        <>
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
        </>
    );
}

export default Product;