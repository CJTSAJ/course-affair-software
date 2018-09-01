const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    recentNotification: [],
    recentHomework: [],
    images: ["/images/pic1.jpg", "/images/pic2.jpg", "/images/pic3.jpg"],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 3000,
    duration: 1000,
    loadingHidden: false,
    informationData0:"作业",
    informationData1: "公告",
    showNotification: false,
    showHomework: false
  },
  /*toDetail:function(){
    wx.navigateTo({
      url: '/pages/homework/homework?content={{value.homeworkContent}}&time={{value.hwdate}}&deadline={{value.deadline}}',
    })
  },*/
  toDetail1: function () {
    wx.navigateTo({
      url: '/pages/notice/notice',
    })
  },
  confirm:function(){
    this.setData({
      showModal:true
    })
  },
  cancel: function () {
    this.setData({
      showModal: false
    })
  },
  onShow:function(){
    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'hibernate/getRecentNotification',
      data:{
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        if(res.data.length != 0){
          console.log(res.data)
          var temp = res.data;
          for (var i = 0; i < temp.length; i++) {
            var newDate = new Date();
            var timestamp = Date.parse(new Date(res.data[i].notificationDate));
            newDate.setTime(timestamp);
            temp[i].notificationDate = newDate.toLocaleDateString() + ' ' + newDate.getHours() + ':' + newDate.getMinutes();
          }

          console.log(temp[0].notificationDate);
          self.setData({
            recentNotification: temp,
            showNotification: true
          })
        }else{
          console.log("没有");
        }
        
      }
    })
    wx.request({
      url: app.globalData.serverUrl + 'getRecentHomework',
      data: {
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data)
        if(res.data.length != 0){
          var temp = res.data;
          for (var i = 0; i < temp.length; i++) {
            var newDate = new Date();
            var timestamp = Date.parse(new Date(res.data[i].hwdate));
            newDate.setTime(timestamp);
            temp[i].hwdate = newDate.toLocaleDateString() + ' ' + newDate.getHours() + ':' + newDate.getMinutes();
          }

          self.setData({
            recentHomework: temp,
            showHomework: true
          })
        }else{
          console.log("没有")
        }
        
      }
    })
    /*wx.redirectTo({
      url: '/pages/getUserInfo/getUserInfo',
    })
    <view class='information' bindtap='toDetail'>
    <view class='title'>
      <text class='titleContent'>{{informationData0}}</text>
      <text class='time'>{{time0}}</text>
    </view>
    <view class='content'>
      <text>{{content0}}</text>
    </view>
  </view>
  <view class='information' bindtap='toDetail1'>
    <view class='title'>
      <text class='titleContent'>{{informationData1}}</text>
      <text class='time'>{{time1}}</text>
    </view>
    <view class='content'>
      <text>{{content1}}</text>
    </view>
  </view>*/
  },
  toManage: function(){
    wx.navigateTo({
      url: '/pages/manage/manage',
    })
  },
  toRollcall:function(){
    wx.navigateTo({
      url: '/pages/rollcall/rollcall',
    })
  }
})