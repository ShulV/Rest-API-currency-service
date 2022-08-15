import React, { useState, useEffect } from 'react'
import Form from './Form'
import Chart from './Chart'
import PeriodCurrencyList from './PeriodCurrencyList'
import './css/style.css'
import formatDate from '../utils/dateMethods'



const CurrencyByPeriod = (props) => {

    const [chartData, setChartData] = useState({
        datasets: [],
    })
    const [chartOptions, setChartOptions] = useState({})
    const [periodCurrencies, setPeriodCurrencies] = useState([])

    const [fromDate, setFromDate] = useState(formatDate(new Date()))
    const [toDate, setToDate] = useState(formatDate(new Date()))
    const [selectOption, setSelectOption] = useState('USD')

    const getLabelList = (currencies) => {
      const labelList = []
      currencies.forEach(currency => {
        // console.log('foreach')
        // console.log(currency)
        labelList.push(formatDate(new Date(currency.date)))
      });
      return labelList
    }

    const getDataList = (currencies) => {
      const dataList = []
      currencies.forEach(currency => {
        dataList.push(currency.value)
      });
      return dataList
    }

    const updateChartData = (currencies, selectOption) => {
      // console.log("----------------------------------------------------------------------------")
      // console.log("updateChartData(currencies, selectOption)")
      // console.log("currencies")
      // console.log(currencies)
      // console.log("selectOption")
      // console.log(selectOption)

      const labels = getLabelList(currencies)
      const data = getDataList(currencies)
      setChartData({
        labels: labels,
        datasets: [
          {
            label: selectOption,
            data: data,
            borderColor: "rgb(73, 211, 146)",
            backgroundColor: "rgba(73, 211, 146, 0.6)",
          }
        ]
      })
    }

    useEffect(() => {    
        setChartOptions({
          responsive: true,
          scales: {
            xAxes: [{
              gridLines: {
                display: false,
                color: "black"
              },
              scaleLabel: {
                display: true,
                labelString: "Time in Seconds",
                fontColor: "red"
              }
            }],
            yAxes: [{
              gridLines: {
                color: "black",
                borderDash: [2, 5],
              },
              scaleLabel: {
                display: true,
                labelString: "Speed in Miles per Hour",
                fontColor: "green"
              }
            }]
          },
          plugins: {
            legend: {
              position: "top"
            },
            title: {
              display: true,
              text: "some text"
            },

            
          




          },
          
        })
    }, [])

    return (
        <div className="currency-by-period">
            <div className='currency-by-period__text-block'>
              {/* Форма запроса валюты за период */}
              <Form 
                currencies={props.currencies} 
                setPeriodCurrencies={setPeriodCurrencies} 
                periodCurrencies={periodCurrencies}
                selectOption={selectOption}
                setSelectOption={setSelectOption}
                setFromDate={setFromDate}
                setToDate={setToDate}
                updateChartData={updateChartData}
                className='currency-by-period__form'
              />
              {/* Список дата-значение для валюты */}
              {periodCurrencies.length > 0 &&
                <PeriodCurrencyList 
                  selectOption={selectOption}
                  periodCurrencies={periodCurrencies} 
                />
              }
              
            </div>
            {/* График */}
            {periodCurrencies.length > 2 &&
                <Chart 
                    chartOptions={chartOptions}
                    chartData={chartData}
                />
            }

            
        </div>
    )
}

export default CurrencyByPeriod