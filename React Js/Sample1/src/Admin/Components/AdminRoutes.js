import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Category from '../Category';
import Product from '../Product';

const AdminRoutes = () => {
    return <>
        <Switch>
            <Route exact path="/Admin/Category" component={Category} />
            <Route exact path="/Admin/Product" component={Product} />
        </Switch>
    </>;
}

export default AdminRoutes;