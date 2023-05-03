import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from '../Home';

const CustomerRoute = () =>{
    return(
        <>
            <Switch>
                <Route default exact path="/" component={Home}/>
            </Switch>
        </>
    );
}

export default CustomerRoute;