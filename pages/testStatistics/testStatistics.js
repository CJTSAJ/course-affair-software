var wxCharts = require('../../utils/wxcharts.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  onLoad: function(){
    wx.getSystemInfo({
      success:function(res){
        console.log(res.windowWidth);
        console.log(res.windowHeight);
      }
    })
    new wxCharts({
      canvasId: 'columnCanvas',
      type: 'column',
      animation: true,
      categories: ['201707', '201708', '201709', '201710', '201711', '201712'],
      series: [{
        name: '人数',
        data: [25, 10, 20, 30, 40, 35],
        /*format: function (val, name) {
          return val.toFixed(2) + '万';
        }*/
      }],
      yAxis: {
        /*format: function (val) {
          return val + '万';
        },*/
        title: '',
        min: 0
      },
      xAxis: {
        disableGrid: false,
        type: 'calibration'
      },
      extra: {
        column: {
          width: 15
        }
      },
      width: 375,
      height: 250,
    });
  }
})