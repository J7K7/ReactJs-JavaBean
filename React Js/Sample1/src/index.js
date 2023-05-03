import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import AdminLayout from './Admin/AdminLayout';
import Category from './Admin/Category';
import CategoryAddEdit from './Admin/CategoryAddEdit';
import Product from './Admin/Product';
import ProductAddEdit from './Admin/ProductAddEdit';
import CustomerLayout from './Customer/CustomerLayout';
import reportWebVitals from './reportWebVitals';
import RetailerLayout from './Retailer/RetailerLayout';

ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route path="/Admin" component={AdminLayout}/>
      <Route path="/Retailer" component={RetailerLayout}/>
      <Route default exact path="/" component={CustomerLayout}/>
      
    </Switch>
  </BrowserRouter>,
  document.getElementById('root')
);

reportWebVitals();
