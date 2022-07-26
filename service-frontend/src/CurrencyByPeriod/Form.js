import React, {useState, useEffect} from 'react'
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
    const [selectOption, setSelectOption] = useState('USD')
    const [fromDate, setFromDate] = useState(formatDate(new Date()))
    const [toDate, setToDate] = useState(formatDate(new Date()))
    const [periodCurrencies, setPeriodCurrencies] = useState([])

    async function currencyFetch() {
        fetch(`http://localhost:8080/api/currency/period-currencies?fromDate=${fromDate}&toDate=${toDate}&charcode=${selectOption}`)
        .then(response => response.json())
        .then(periodCurrencies => {
          setPeriodCurrencies(periodCurrencies)
        })
    }

    async function getDataForPeriod() {
        console.log(`selectOption = ${selectOption}, minDate = ${fromDate}, maxDate = ${toDate}`)

        await currencyFetch()

        await console.log(periodCurrencies)

        // TODO сделать 

    }
    
    const fromDateHandler = (event) => {
        setFromDate(event.target.value)
    }

    const toDateHandler = (event) => {
        setToDate(event.target.value)
    }

    const selectOptionHandler = (event) => {
        setSelectOption(getCharcode(event.target.value))
    }

    const getCharcode = (currencyName) => {
        return props.currencies.filter((currency) => {
            return currency.name == currencyName
        })[0].charcode
    }

    return (
        <form className='currency-by-period__form'>
            <Select currencies={props.currencies} selectOptionHandler={selectOptionHandler}/>
            <div>
                <span>От:</span>
                <input type="date" min="2000-01-01" max={formatDate(new Date())} onChange={fromDateHandler} ></input>
            </div>
            <div>   
                <span>До:</span>
                <input type="date" min="2000-01-01" max={formatDate(new Date())} onChange={toDateHandler} ></input>     
            </div>
            <input type='button' value='Получить данные' onClick={getDataForPeriod}></input>
        </form>
    )
}

export default Form