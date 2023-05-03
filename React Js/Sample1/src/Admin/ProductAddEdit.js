import React, { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';

const ProductAddEdit = (props) => {

    const [getName, setName] = useState("");
    const [getPrice, setPrice] = useState();
    const [getDescription, setDescription] = useState("");
    const [getActive, setActive] = useState(false);
    const [getCategoryId, setCategoryId] = useState(0);

    const [getCategory, setCategory] = useState({});

    const [getTitle, setTitle] = useState("Add Product");

    const FormData = (event) => {

        if (event.target.name == "ddlCategory")
            setCategoryId(event.target.value);

        else if (event.target.name == "txtName")
            setName(event.target.value);

        else if (event.target.name == "rdActive")
            setActive(event.target.value);

        else if (event.target.name == "txtPrice")
            setPrice(event.target.value);

        else if (event.target.name == "txtDescription")
            setDescription(event.target.value);

    }

    const ProductSubmit = async (event) => {
        event.preventDefault();

        if (props.Operation == "Edit") {
            const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/Put/" + props.Id + "/" + getName + "/" + getPrice + "/" + getDescription + "/" + getActive + "/" + getCategoryId, {
                method: "POST"
            });
            const data = await response.json();

            if (data.Message == "Record Updated.") {
                setName("");
                setPrice();
                setDescription("");
                setActive(false);
                setCategoryId(0);
            }
        } else {
            const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/Post/" + getName + "/" + getPrice + "/" + getDescription + "/" + getActive + "/" + getCategoryId, {
                method: "POST"
            });
            const data = await response.json();

            if (data.Message == "Record Inserted.") {
                setName("");
                setPrice();
                setDescription("");
                setActive(false);
                setCategoryId(0);
            }
        }
    }

    useEffect(() => {

        const GetCategory = async () => {

            const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Get");
            const data = await reponse.json();

            setCategory(data);
        };

        if (props.Id != undefined) {
            const GetProductById = async () => {

                const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/GetById/" + props.Id);
                const data = await reponse.json();

                setName(data.name);
                setPrice(data.price);
                setDescription(data.description);
                setActive(data.isActive);
                setCategoryId(data.category.id);
            };

            const DeleteProduct = async () => {

                const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Product/Delete/" + props.Id, {
                    method: "POST"
                });

                const data = await response.json();
            };


            if (props.Operation == "Edit") {
                setTitle("Edit Product");
                GetProductById();
            } else if (props.Operation == "Delete") {
                setTitle("Delete Product");
                DeleteProduct();
            }
        }

        GetCategory();
    }, []);



    return (
        <>
            <PageTitle Title={getTitle} />
            <div className="row">
                <div className="col-8">
                    <div className="card">
                        <div className="card-body">
                            <form className="floating-labels m-t-40" onSubmit={ProductSubmit}>
                                <div className="form-group">
                                    <select className="form-control p-0" name="ddlCategory" required defaultValue={getCategoryId} onBlur={FormData}>
                                        <option value="0"></option>
                                        {
                                            Object.keys(getCategory).map(index => {
                                                return <option value={getCategory[index].id}>{getCategory[index].name}</option>
                                            })
                                        }
                                    </select>
                                    <span className="bar"></span>
                                    <label htmlFor="ddlCategory">Category</label>
                                </div>
                                <div className="form-group">
                                    <input type="text" className="form-control" name="txtName" required defaultValue={getName} onBlur={FormData} />
                                    <span className="bar"></span>
                                    <label htmlFor="txtName">Name</label>
                                </div>
                                <div className="form-group">
                                    <input type="number" className="form-control" name="txtPrice" required min={0} step="any" defaultValue={getPrice} onBlur={FormData} />
                                    <span className="bar"></span>
                                    <label htmlFor="txtPrice">Price</label>
                                </div>
                                <div className="form-group">
                                    <input type="text" className="form-control" name="txtDescription" defaultValue={getDescription} onBlur={FormData} />
                                    <span className="bar"></span>
                                    <label htmlFor="txtDescription">Description</label>
                                </div>
                                <div>
                                    {
                                        getActive == true ? (<>
                                            <input name="chkActive" type="radio" className="with-gap" id="ActiveTrue" value={true} onChange={FormData} checked />
                                            <label htmlFor="ActiveTrue">Active</label>
                                            <input name="chkActive" type="radio" className="with-gap" id="ActiveFalse" value={false} onChange={FormData} />
                                            <label htmlFor="ActiveFalse">In-active</label>
                                        </>) : (<>
                                            <input name="chkActive" type="radio" className="with-gap" id="ActiveTrue" value={true} onChange={FormData} />
                                            <label htmlFor="ActiveTrue">Active</label>
                                            <input name="chkActive" type="radio" className="with-gap" id="ActiveFalse" value={false} onChange={FormData} checked />
                                            <label htmlFor="ActiveFalse">In-active</label>
                                        </>
                                        )
                                    }
                                </div>
                                <div className="form-group mt-4">
                                    <button type="submit" className="btn btn-info">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default ProductAddEdit;