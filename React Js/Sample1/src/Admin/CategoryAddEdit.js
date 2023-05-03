import React, { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet';
import TopNavBar from './Components/TopNavBar';
import Footer from './Components/Footer';
import LeftNavBar from './Components/LeftNavBar';
import PageTitle from './Components/PageTitle';
// import AdminRoutes from './Components/AdminRoutes';
import Perloader from './Components/Perloader';

const CategoryAddEdit = (props) => {

    const [getName, setName] = useState("");
    const [getActive, setActive] = useState(false);
    const [getParentId, setParentId] = useState(0);

    const [getCategory, setCategory] = useState({});

    const [getTitle, setTitle] = useState("Add Category");

    const FormData = (event) => {

        if (event.target.name == "txtName")
            setName(event.target.value);

        else if (event.target.name == "ddlParent")
            setParentId(event.target.value);

        else if (event.target.name == "rdActive")
            setActive(event.target.value);

    };

    const CategorySubmit = async (event) => {
        event.preventDefault();

        if (props.Operation == "Edit") {
            const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Put/" + props.Id + "/" + getName + "/" + getActive + "/" + getParentId, { method: "POST", });
            const data = await reponse.json();

            if (data.Message == "Record Updated.") {
                setName("");
                setActive(false);
                setParentId(0);
            }
        } else {
            const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Post/" + getName + "/" + getActive + "/" + getParentId, { method: "POST", });
            const data = await reponse.json();

            if (data.Message == "Record Inserted.") {
                setName("");
                setActive(false);
                setParentId(0);
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

            const GetCategoryById = async () => {

                const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/GetById/" + props.Id);
                const data = await reponse.json();

                setName(data.name);
                setActive(data.isActive);
                if (data.parentId != undefined) {
                    setParentId(data.parentId.id);
                }
            };

            const DeleteCategory = async () => {

                const reponse = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Delete/" + props.Id, {
                    method: "POST"
                });

                const data = await reponse.json();
            };


            if (props.Operation == "Edit") {
                setTitle("Edit Category");
                GetCategoryById();
            } else if (props.Operation == "Delete") {
                setTitle("Delete Category");
                DeleteCategory();
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
                            <form className="floating-labels m-t-40" onSubmit={CategorySubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control" name="txtName" required defaultValue={getName} onBlur={FormData} />
                                    <span className="bar"></span>
                                    <label htmlFor="txtName">Name</label>
                                </div>
                                <div className="form-group">
                                    <select className="form-control p-0" name="ddlParent" required defaultValue={getParentId} onBlur={FormData}>
                                        <option value="0"></option>
                                        {
                                            Object.keys(getCategory).map(index => {
                                                return <option value={getCategory[index].id}>{getCategory[index].name}</option>
                                            })
                                        }
                                    </select>
                                    <span className="bar"></span>
                                    <label htmlFor="ddlParent">Parent Category</label>
                                </div>
                                <div>
                                    {
                                        getActive == true ? (<>
                                            <input name="rdActive" type="radio" className="with-gap" id="ActiveTrue" value={true} onChange={FormData} checked />
                                            <label htmlFor="ActiveTrue">Active</label>
                                            <input name="rdActive" type="radio" className="with-gap" id="ActiveFalse" value={false} onChange={FormData} />
                                            <label htmlFor="ActiveFalse">In-active</label>
                                        </>) : (<>
                                            <input name="rdActive" type="radio" className="with-gap" id="ActiveTrue" value={true} onChange={FormData} />
                                            <label htmlFor="ActiveTrue">Active</label>
                                            <input name="rdActive" type="radio" className="with-gap" id="ActiveFalse" value={false} onChange={FormData} checked />
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

export default CategoryAddEdit;