// pages/signSituation/signSituation.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allSign: []
  },

  onShow:function(){
    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'getAllSign',
      data:{
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function(res){
        res.data.reverse();
        console.log(res.data);









        
        var temp = res.data;
        for (var i = 0; i < temp.length; i++) {
          var newDate = new Date();
          var timestamp = Date.parse(new Date(res.data[i].signDate));
          newDate.setTime(timestamp);
          temp[i].signDate = newDate.toLocaleDateString() + ' ' + newDate.getHours() + ':' + newDate.getMinutes();
        }
        self.setData({
          allSign: temp
        })
      }
    })
  },

  toSign:function(e){
    console.log(e.currentTarget.dataset.id);
    wx.navigateTo({
      url: '/pages/signData/signData',
    })
  }
})