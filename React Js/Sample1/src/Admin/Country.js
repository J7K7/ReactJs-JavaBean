import React, { useEffect, useState } from 'react'

const Country = () => {

    const [getCountries, setCountries] = useState({});
    const [getCities, setCities] = useState({});

    // https://countriesnow.space/api/v0.1/countries/iso => Get All Countries with ISO Code
    // https://countriesnow.space/api/v0.1/countries/codes => Get All Countries with their Code
    // https://countriesnow.space/api/v0.1/countries/flag/images => Get Countries with thier Flag
    // https://countriesnow.space/api/v0.1/countries/capital => Get Countries with their Capital
    // https://countriesnow.space/api/v0.1/countries/states => Get Countries with their State  // {country: AF} => Get Single Country State
    // https://countriesnow.space/api/v0.1/countries => Get All Countries with their Cities

    // https://countriesnow.space/api/v0.1/countries/state/cities => Get City by State


    useEffect(() => {
        const Countries = async () => {
            const response = await fetch("https://countriesnow.space/api/v0.1/countries/flag/images");
            // const response = await fetch("https://countriesnow.space/api/v0.1/countries/states", {
            //     method: "POST",
            //     headers: {
            //         "content-type": "application/json",
            //     },
            //     body: JSON.stringify({
            //         "country": "India",
            //     }),
            // });
            // const response = await fetch("https://countriesnow.space/api/v0.1/countries/state/cities", {
            //     method: "POST",
            //     headers: {
            //         "content-type": "application/json",
            //     },
            //     body: JSON.stringify({
            //         "country": "India",
            //         "state": "Gujarat",
            //     }),
            // });
            const data = await response.json();
            console.log("JSON Country API Response", data.data);
            setCountries(data.data);
            // console.log("JSON State API Response", data.data.states);
            // setCountries(data.data.states);
            // console.log("JSON City API Response", data.data);
            // setCities(data.data);
        };

        Countries();
    }, []);

    return <>
        <table border="1">
            <tbody>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Flag</td>
                </tr>
                {
                    Object.keys(getCountries).map(index => {
                        return <tr key={index}>
                            <td>{index}</td>
                            <td>{getCountries[index].name}</td>
                            <td><img src={getCountries[index].flag} width="50" /></td>
                        </tr>
                    })
                    /* Object.keys(getCities).map(index => {
                        return <tr key={index}>
                            <td>{index}</td>
                            <td>{getCities[index]}</td>
                            <td><img src={getCities[index].flag} width="50" /></td>
                        </tr>
                    }) */
                }
            </tbody>
        </table>
    </>
}

export default Country;