const app = getApp()
// pages/allManager/allManager.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    size: 0,
    allTa:[],
    allTeacher: []
  },
  onShow:function(){
    console.log("allManager onshow")
    var self = this;
    wx.request({
      url: 'http://207.148.114.118:8080/courseAffair/getTeacherAndTa',
      data: {
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data.ta[0])
        self.setData({
          allTa: res.data.ta,
          allTeacher: res.data.teacher,
          size: res.data.ta.length
        })
      }
    })
  },
  deleteTa:function(e){
    var self = this;
    var index = e.target.dataset.id;

    console.log(self.data.allTa[2].taGroupId);
    console.log(index);
    wx.showModal({
      title: '提示',
      content: '确定取消该助教身份',
      success: function (res) {
        if (res.confirm) {
          var taGroupId = self.data.allTa[index].taGroupId
          var taid = self.data.allTa[index].taid
          var newArr = self.data.allTa
          newArr.splice(index, 1);
          self.setData({
            allTa: newArr,
            size: newArr.length
          })

          wx.request({
            url: 'http://207.148.114.118:8080/courseAffair/deleteTa',
            data: {
              opengid: taGroupId,
              openid: taid
            },
            method: 'POST',
            header: { 'content-type': 'application/json' },
            success:function(){
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  addManger:function(){
    wx.navigateTo({
      url: '/pages/managerSetting/mangerSetting',
    })
  }
})