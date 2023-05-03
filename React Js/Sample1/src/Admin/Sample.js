import React, { useEffect, useState } from 'react'

export default function Sample() {

  const [getCategory, setCategory] = useState({});

  function CallAPI() {
    // var Response = fetch("http://localhost:8080/JavaEJB/webresources/Admin/Project/Get", {
    //   method: "GET",
    //   mode: "cors",
    //   headers: {
    //     "Accept" : "*/*",
    //     // "Access-Control-Allow-Origin": "*/*",
    //   },
    // })
    // .then(res => res.json())
    // .then(data => console.log(data))
    // fetch("http://localhost:8080/JavaEJB/webresources/Admin/Departement/Category/Post", {
    //   method: "POST",
    //   mode: "no-cors"
    // });
  }

  useEffect(() => {
    const Category = async () => {
        const response = await fetch("http://localhost:8080/RetailShop/webresources/RetailShop/Category/Get")
        const data = response.json();
        console.log("JSON Country API Response", data);
        setCategory(data);
    };

    Category();
}, []);

  return (
    <>
      {
        
      }
      {/* {CallAPI()} */}
    </>
  )
}
