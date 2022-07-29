import React, { useState, useEffect } from 'react'
import Form from './Form'
import Chart from './Chart'
import PeriodCurrencyList from './PeriodCurrencyList'
import './css/style.css'



const CurrencyByPeriod = (props) => {

    const [chartData, setChartData] = useState({
        datasets: [],
    })
      
    const [chartOptions, setChartOptions] = useState({})
      
    useEffect(() => {
        setChartData({
          labels: ["John", "Micle", "Kevin", "Oreo"],
          datasets: [
            {
              label: "Some label",
              data: [12, 55, 34, 120],
              borderColor: "rgb(53, 162, 235)",
              backgroundColor: "rgba(53, 162, 235, 0.4",
            }
          ]
        })
        setChartOptions({
          responsive: true,
          plugins: {
            legend: {
              position: "top"
            },
            title: {
              display: true,
              text: "some text"
            }
          }
        })
    }, [])

    const [periodCurrencies, setPeriodCurrencies] = useState([])
    const [selectOption, setSelectOption] = useState('USD')

    return (
        <div>
            <Form 
                currencies={props.currencies} 
                setPeriodCurrencies={setPeriodCurrencies} 
                periodCurrencies={periodCurrencies}
                selectOption={selectOption}
                setSelectOption={setSelectOption}
                className='currency-by-period__form'
            />
            <Chart 
                // periodCurrencies={periodCurrencies} 
                // selectOption={selectOption}
                chartOptions={chartOptions}
                chartData={chartData}
            />
            <PeriodCurrencyList 
                selectOption={selectOption}
                periodCurrencies={periodCurrencies} 
            />
        </div>
    )
}

export default CurrencyByPeriod