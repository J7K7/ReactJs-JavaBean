import React from 'react';
import { Link } from 'react-router-dom';

const LeftNavBar = () => {
    function Html(Url, Icon, Text) {
        return (
            <li>
                <Link className="waves-effect waves-dark" to={Url} >
                    <i className={"mdi " + Icon}></i>{Text}
                    {/* <span className="label label-rouded label-themecolor pull-right">4</span> */}
                </Link>
            </li>);
    }
    return <>
        <aside className="left-sidebar">
            <div className="scroll-sidebar">
                <nav className="sidebar-nav">
                    <ul id="sidebarnav">
                        <li className="nav-devider"></li>
                        {Html("/Admin/Category", "mdi-gauge", "Category")}
                        {Html("/Admin/Product", "mdi-email", "Product")}
                        {Html("Product", "mdi-chart-bubble", "Product")}
                        {Html("Product", "mdi-file", "Product")}
                        {Html("Product", "mdi-table", "Product")}
                        {Html("Product", "mdi-email", "Product")}
                        {Html("Product", "mdi-widgets", "Product")}
                        {Html("Product", "mdi-book-multiple", "Product")}
                        {Html("Product", "mdi-book-open-variant", "Product")}
                        {Html("Product", "mdi-file-chart", "Product")}
                        {Html("Product", "mdi-brush", "Product")}
                        {Html("Product", "mdi-map-marker", "Product")}
                        {Html("Product", "mdi-arrange-send-backward", "Product")}
                    </ul>
                </nav>
            </div>
        </aside>
    </>;
}

export default LeftNavBar;