import React, { useEffect, useState } from 'react'
import { getDropdownData } from '../service/ReportService';
import { searchRecords } from '../service/ReportService';
import { exportExcel } from '../service/ReportService';
import { exportPdf } from '../service/ReportService';
import './ReportForm.css';
function ReportForm() {

  const[plans,setPlans]=useState([]);
  const[statuses,setStatuses]=useState([]);
  const[searchData, setSearchData]=useState({
    planId:"",
    statusId:"",
    gender:"",
     startDate: "",
    endDate: ""
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

// download excel

const downloadExcel = () => {

    exportExcel(searchData)
        .then((response) => {

            const url = window.URL.createObjectURL(
                new Blob([response.data])
            );

            const link = document.createElement("a");

            link.href = url;

            link.download = "Citizen_Report.xlsx";

            document.body.appendChild(link);

            link.click();

            link.remove();

        })
        .catch((error) => {
            console.log(error);
        });

};


//pdf download
const downloadPdf = () => {

    exportPdf(searchData)
        .then((response) => {

            const url = window.URL.createObjectURL(
                new Blob([response.data])
            );

            const link = document.createElement("a");

            link.href = url;

            link.download = "Citizen_Report.pdf";

            document.body.appendChild(link);

            link.click();

            link.remove();

        });

};

  return (
    <div className="report-container">
      <div>
        <h2>Citizen Reports</h2>
      <label>Plan Name:</label>
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
<br />
<div>
  <label>Start Date:</label>

  <input
    type="date"
    value={searchData.startDate}
    onChange={(e) =>
      setSearchData({
        ...searchData,
        startDate: e.target.value
      })
    }
  />
</div>

<br />

<div>
  <label>End Date:</label>

  <input
    type="date"
    value={searchData.endDate}
    onChange={(e) =>
      setSearchData({
        ...searchData,
        endDate: e.target.value
      })
    }
  />
</div>

<br />

<button onClick={handleSearch}>
    Search
</button>

<hr />


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

<hr />
<h3>Export Results</h3>

<button onClick={downloadExcel} style={{margin:"10px"}}>
    Export Excel
</button>

<button onClick={downloadPdf}>
    Export PDF
</button>

    </div>
  )
}

export default ReportForm