import React, { useEffect, useState } from 'react';
import './App.css';
import Slider from './TodayCurrencies/Slider';
import CurrencyByPeriod from './CurrencyByPeriod/CurrencyByPeriod';
import formatDate from './utils/dateMethods';
import Loader from './Loader/Loader';

function App() {

  const [currencies, setCurrencies] = useState([])
  const [currenciesLoaded, setCurrenciesLoaded] = useState(true);
  

  //начальные данные для слайдера и выпадающего списка
  useEffect(() => {
    fetch(`http://localhost:8080/api/currency/all-currencies-for-day?date=${formatDate(new Date())}`)
    .then(response => response.json())
    .then(currencies => {
      setCurrencies(currencies)
    }).then(() => setCurrenciesLoaded(false))
  }, [])


  return (
    <main className="app">
      <section className="section">
        <h1 className='section-header'>Курсы валют на { formatDate(new Date()) } (сегодня)</h1>
        {currenciesLoaded && <Loader/>}
        {!currenciesLoaded && <Slider currencies={currencies}/>}

        
      </section>
      <section className="section">
        <h1 className='section-header'>Курсы валют по периоду</h1>
        <CurrencyByPeriod 
          currencies={currencies}
          currenciesLoaded={currenciesLoaded}
        />
      </section>
    </main>
  );
}

export default App;
