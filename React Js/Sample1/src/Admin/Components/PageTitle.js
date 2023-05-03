import React from 'react';

const PageTitle = (props) => {

    const AddPageURL = (AddPageURL) => {
        return <div className="col-md-6 align-self-center text-right">
            <button type="button" className="btn waves-effect col-md-2 waves-light btn-secondary" title={AddPageURL}>Add</button>
        </div>
    }

    return <>
        <div className="row page-titles">
            <div className="col-md-6 align-self-center">
                <h3 className="text-inverse">{props.Title}</h3>{/* text-themecolor */}
            </div>
            { props.AddPageURL == null ? "" : AddPageURL(props.AddPageURL) }
        </div>
    </>;
}

export default PageTitle;