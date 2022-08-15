import React, {useState} from 'react'
import Select from './Select'
import './css/style.css'
import formatDate from '../utils/dateMethods'


  
const Form = (props) => {
    
    const [fromDate, setFromDate] = useState(formatDate(new Date())) //по умолчанию текущая дата
    const [toDate, setToDate] = useState(formatDate(new Date())) //по умолчанию текущая дата
    const [selectOption, setSelectOption] = useState("AUD") //по умолчанию Австралийский доллар
    

    async function currencyFetch() {
        // console.log("----------------------------------------------------------------------------")
        // console.log("currencyFetch()")
        // console.log(`http://localhost:8080/api/currency/period-currencies?fromDate=${fromDate}&toDate=${toDate}&charcode=${selectOption}`)
        const response = await fetch(`http://localhost:8080/api/currency/period-currencies?fromDate=${fromDate}&toDate=${toDate}&charcode=${selectOption}`)
        if (response.ok) {
            let periodCurrencies = null;
            periodCurrencies = await response.json()
            // console.log("periodCurrencies = await response.json()")
            // console.log(periodCurrencies)
            props.setPeriodCurrencies(periodCurrencies)
            props.setFromDate(fromDate)
            props.setToDate(toDate)
            props.updateChartData(periodCurrencies, selectOption)
        }
        else {
            alert(`Запрос не выполнен. Ответ сервера ${response.status}`)
        }
        
    }

    async function getDataForPeriod() {
        
        props.setSelectOption(selectOption)
        props.setFromDate(fromDate)
        props.setToDate(toDate)
        await currencyFetch()
        
        // await console.log(props.periodCurrencies)

    }
    
    const fromDateHandler = (event) => {
        setFromDate(event.target.value)
    }

    const toDateHandler = (event) => {
        setToDate(event.target.value)
    }
    

    const selectOptionHandler = (event) => {
        // console.log("======selectOptionHandler        getCharcode(event.target.value) = " + getCharcode(event.target.value))
        setSelectOption(getCharcode(event.target.value))
    }

    const getCharcode = (currencyName) => {
        return props.currencies.filter((currency) => {
            return currency.name == currencyName
        })[0].charcode
    }

    return (
        <div className="currency-by-period__form-block">
            <h2 className='section-header-2'>Форма</h2>
            <form className='currency-by-period__form'>
                <Select 
                currencies={props.currencies}
                selectOptionHandler={selectOptionHandler}
                />
                <div className="currency-by-period__from-date-block">
                    <span>От:</span>
                    <input  className="currency-by-period__from-date" type="date" min="2000-01-01" max={formatDate(new Date())} onChange={fromDateHandler} ></input>
                </div>
                <div className="currency-by-period__to-date-block">   
                    <span>До:</span>
                    <input className="currency-by-period__to-date" type="date" min="2000-01-01" max={formatDate(new Date())} onChange={toDateHandler} ></input>     
                </div>
                <input className="currency-by-period__form-btn" type='button' value='Получить данные' onClick={getDataForPeriod}></input>
            </form>
        </div>
        
    )
}

export default Form