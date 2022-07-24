import React from 'react'
import Select from './Select'
import './css/style.css'

function formatDate(date) {

    var dd = date.getDate();
    if (dd < 10) dd = '0' + dd;
  
    var mm = date.getMonth() + 1;
    if (mm < 10) mm = '0' + mm;
  
    var yyyy = date.getFullYear();
  
    return `${yyyy}-${mm}-${dd}`
  }

  
const Form = (props) => {

    const getDataForPeriod = () => {
       
    }

    return (
        <form className='currency-by-period__form'>
            <Select currencies={props.currencies}/>
            <div>
                <span>От:</span>
                <input type="date" min="2000-01-01" max={formatDate(new Date())}></input>
            </div>
            <div>   
                <span>До:</span>
                <input type="date" min="2000-01-01" max={formatDate(new Date())}></input>     
            </div>
            <input type='button' value='Получить данные' onClick={getDataForPeriod}></input>
        </form>
    )
}

export default Form