import React, { useEffect, useState } from 'react'
import { getDropdownData } from '../service/ReportService';
import { searchRecords } from '../service/ReportService';
function ReportForm() {

  const[plans,setPlans]=useState([]);
  const[statuses,setStatuses]=useState([]);
  const[searchData, setSearchData]=useState({
    planId:"",
    statusId:"",
    gender:""
  });
  const [records, setRecords] = useState([]);


   useEffect(() => {
    loadDropdownData();
  }, []);

  const loadDropdownData=()=>{
 getDropdownData().then((response)=>{
console.log(response.data);
setPlans(response.data.plans);
setStatuses(response.data.statuses);
 })
 .catch((error)=>{
 console.log(error);
 });
  };

const handleSearch=()=>{
    const request=searchData;
     searchRecords(request)
        .then((response) => {
            setRecords(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    console.log(request)
}


  return (
    <div style={{display:'flex',flexDirection:"column",justifyContent:'center',alignItems:'center',boxShadow:0}}>
      <div>
        <h2>Citizen Reports</h2>
      <label>Plane Name:</label>
      <select value={searchData.planId} onChange={(e)=>setSearchData({
      ...searchData,
      planId: e.target.value
    })}>
        <option value="">--Select Plan--</option>
        {
          plans.map((plan)=>(
            <option key={plan.planId} value={plan.planId}>{plan.planName}</option>
          ))
        }
      </select>
    </div>
    <br />

    <div>
      <label>Plan Status:</label>
      <select
  value={searchData.statusId}
  onChange={(e) =>
    setSearchData({
      ...searchData,
      statusId: e.target.value
    })
  }
>
        <option value="">--Select Status--</option>
        {
          statuses.map((status)=>(
          <option
                key={status.statusId}
                value={status.statusId}
              >
                {status.statusName}
              </option>
        )

        )
        }
        
      </select>
    </div>
     <br />
<div>
  <label>Gender:</label>
  <select
  value={searchData.gender}
  onChange={(e) =>
    setSearchData({
      ...searchData,
      gender: e.target.value
    })
  }
>
    <option value="">--Select Gender--</option>
    <option value="Male">Male</option>
    <option value="Female">Female</option>
  </select>
</div>

<button onClick={handleSearch}>
    Search
</button>

<hr />

<h3>Search Results</h3>

<table border="1">

  <thead>
    <tr>
      <th>Citizen Id</th>
      <th>Citizen Name</th>
      <th>Gender</th>
      <th>Plan Name</th>
      <th>Status</th>
      <th>Benefit Amount</th>
    </tr>
  </thead>

  <tbody>

    {
      records.map((record) => (

        <tr key={record.citizenId}>

          <td>{record.citizenId}</td>

          <td>{record.citizenName}</td>

          <td>{record.citizenGender}</td>

          <td>{record.planName}</td>

          <td>{record.planStatus}</td>

          <td>{record.benefitAmount}</td>

        </tr>

      ))
    }

  </tbody>

</table>


    </div>
  )
}

export default ReportForm