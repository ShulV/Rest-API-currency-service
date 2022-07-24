import React, { useEffect, useState } from 'react';
import './App.css';
import Slider from './TodayCurrencies/Slider';
import CurrencyByPeriod from './CurrencyByPeriod/CurrencyByPeriod';

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
    </main>
  );
}

export default App;
