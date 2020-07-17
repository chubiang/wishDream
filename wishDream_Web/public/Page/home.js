// module "home.js"
import React, { Component, Fragment } from 'react'
import Axios from 'axios'

import DailyLifeGridList from 'Component/DailyLifeGridList'
import CategoryHelper from '../Component/CategoryHelper'


function Home() {

  return (
    // Axios.get('/menu')
    //   .then(function (res) {
          
    //   }).catch(function (error) {
    //       console.log(error)
    //   })
    <>
      {/* <CategoryHelper menu={} /> */}
      <DailyLifeGridList />
    </>
  )
}

export default Home
