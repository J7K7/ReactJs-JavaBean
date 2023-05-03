import React, { useEffect, useState } from 'react';
import Helmet from 'react-helmet';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';
// import AdminRoutes from './Components/AdminRoutes';

const Category = () => {

    const [getCategory, setCategory] = useState({});

    useEffect(() => {
        const GetCategory = async () => {

            const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Get");
            const data = await reponse.json();

            setCategory(data);
        };

        GetCategory();
    }, []);

    return (
        <>
            <PageTitle Title="Category" AddPageURL="CategoryAdd" />
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card-body">
                            <div className="table-responsive">
                                <table className="table full-color-table full-info-table hover-table color-bordered-table info-bordered-table">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th>Parent Category</th>
                                            <th>Active</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            Object.keys(getCategory).map(index => {
                                                return <tr key={index}>
                                                    <td>{getCategory[index].id}</td>
                                                    <td>{getCategory[index].name}</td>
                                                    <td>{getCategory[index].parentId != undefined ? getCategory[index].parentId.name : null}</td>
                                                    <td>{getCategory[index].isActive.toString()}</td>
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

export default Category;