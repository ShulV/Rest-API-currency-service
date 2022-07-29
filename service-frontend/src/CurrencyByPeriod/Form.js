import React, {useState} from 'react'
import Select from './Select'
import './css/style.css'
import formatDate from '../utils/dateMethods'


  
const Form = (props) => {
    
    // const [currencyName, setCurrencyName] = useState("")

    const [fromDate, setFromDate] = useState(formatDate(new Date()))
    const [toDate, setToDate] = useState(formatDate(new Date()))
    const [selectOption, setSelectOption] = useState("USD")
    

    async function currencyFetch() {
        const response = await fetch(`http://localhost:8080/api/currency/period-currencies?fromDate=${fromDate}&toDate=${toDate}&charcode=${props.selectOption}`)
        if (response.ok) {
            const periodCurrencies = await response.json()
            console.log("response ok: periodCurrencies")
            
            props.setPeriodCurrencies(periodCurrencies)
            console.log(props.periodCurrencies)
            props.updateChartData(props.periodCurrencies, props.selectOption)
        }
        else {
            alert(`Запрос не выполнен. Ответ сервера ${response.status}`)
        }
        
    }

    async function getDataForPeriod() {
        console.log(`selectOption = ${props.selectOption}, minDate = ${fromDate}, maxDate = ${toDate}`)

        await currencyFetch()
        props.setSelectOption(selectOption)
        props.setFromDate(fromDate)
        props.setToDate(toDate)
        await console.log(props.periodCurrencies)

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
            <Select 
                currencies={props.currencies}
                selectOptionHandler={selectOptionHandler}
            />
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