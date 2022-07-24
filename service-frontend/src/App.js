import React, { useEffect, useState } from 'react';
import './App.css';
import Slider from './TodayCurrencies/Slider';
import CurrencyByPeriod from './CurrencyByPeriod/CurrencyByPeriod';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Bar } from 'react-chartjs-2'

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
)


function formatDate(date) {

  var dd = date.getDate();
  if (dd < 10) dd = '0' + dd;

  var mm = date.getMonth() + 1;
  if (mm < 10) mm = '0' + mm;

  var yyyy = date.getFullYear();

  return `${yyyy}-${mm}-${dd}`
}


function App() {

  const [currencies, setCurrencies] = useState([])

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

  // данные для слайдера и выпадающего списка
  useEffect(() => {
    fetch(`http://localhost:8080/api/currency/all-currencies-for-day?date=${formatDate(new Date())}`)
    .then(response => response.json())
    .then(currencies => {
      setCurrencies(currencies)
    })
  }, [])


  return (
    <main className="app">
      <section className="section">
        <h1 className='section-header'>Курсы валют на { formatDate(new Date()) } (сегодня)</h1>
        <Slider currencies={currencies} />
      </section>
      <section className="section">
        <h1 className='section-header'>Курсы валют по периоду</h1>
        <CurrencyByPeriod className='currency-by-period' currencies={currencies} />
      </section>
      <Bar options={chartOptions} data={chartData} />
    </main>
  );
}

export default App;
