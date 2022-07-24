import React, { useEffect, useState } from 'react';
import './App.css';
import Slider from './TodayCurrencies/Slider';

function formatDate(date) {

  var dd = date.getDate();
  if (dd < 10) dd = '0' + dd;

  var mm = date.getMonth() + 1;
  if (mm < 10) mm = '0' + mm;

  var yy = date.getFullYear() % 100;
  if (yy < 10) yy = '0' + yy;

  return dd + '.' + mm + '.' + yy;
}


function App() {

  const [currencies, setCurrencies] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/api/currency/all-today-currencies')
    .then(response => response.json())
    .then(currencies => {
      setCurrencies(currencies)
      console.log(currencies)
    })
  }, [])

  return (
    <main className="app">
      <section className="section">
        <h1 className='section-header'>Курсы валют на { formatDate(new Date()) } (сегодня)</h1>
        <Slider currencies={currencies} />
      </section>
    </main>
  );
}

export default App;
