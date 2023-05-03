import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Dashboard from '../Dashboard';

const RetailerRoute = ()=>{
    return(
        <>
            <Switch>
                <Route path="/Retailer/" component={Dashboard}/>
            </Switch>
        </>
    );
}

export default RetailerRoute;