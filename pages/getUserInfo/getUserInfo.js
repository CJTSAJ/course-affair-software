const app = getApp()
// pages/getUserInfo.js
Page({
  data: {
    formId: '',
    name: '',
    studentID: '',
    nameShow: 'none',
    idShow: 'none',
    identity:null,
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    formId:'',
    openid: app.globalData.openId,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onShow: function (options) {
    if(app.globalData.identity != 'noExist'){
      wx.redirectTo({
        url: '/pages/home/home',
      })
    }
  },
  formSubmit: function (e) {
    var formId = e.detail.formId;
    var that = this;

    this.setData({
      formId: formId
    })
    if(this.data.nameShow == 'none'){
      wx.showModal({
        title: '提示',
        content: '请选择身份',
      })
    }
    else if(this.data.name==''){
      wx.showModal({
        title: '提示',
        content: '姓名不能为空',
      })
    }
    else if (this.data.idShow == '' && this.data.studentID == ''){
      wx.showModal({
        title: '提示',
        content: '学号不能为空',
      })
    }
    else{
      console.log("confirm")
      
      //向后端发送数据
      var isStudent = false;
      if (that.data.idShow == '') {
        isStudent = true
      }

      if(isStudent == true){
        wx.request({
          url: 'http://207.148.114.118:8080/courseAffair/login',
          data: {
            openid: app.globalData.openId,
            opengid: app.globalData.openGId,
            name: that.data.name,
            studentID: that.data.studentID,
            isStudent: isStudent,
            formId: that.data.formId
          },
          method: 'POST',
          header: { 'content-type': 'application/json' },
          success: function (res) {
            console.log("存储信息成功")
            app.globalData.identity = "student";
          },
          fail: function (error) {
            console.log("存储信息失败")

          }
        })
        
      }else{
        //存老师信息
        wx.request({
          url: 'http://207.148.114.118:8080/courseAffair/registerTeacher',
          data:{
            openid: app.globalData.openId,
            opengid: app.globalData.openGId,
            name: that.data.name
          },
          method: 'POST',
          header: { 'content-type': 'application/json' },
          success:function(res){
            app.globalData.identity = "teacher";
          }
        })
      }

      wx.reLaunch({
        url: '/pages/home/home',
      })
      /*var data = {
        openid: app.globalData.openId,
        opengid: app.globalData.openGId,
        name: that.data.name,
        studentID: that.data.studentID,
        isStudent: isStudent
      }*/
      
    }
    
  },
  showPrePage: function () {
    this.setData({
      eye: false
    })
  },
  myOpenSetting: function(){
    var that = this;
    wx.openSetting({
      success: function (res) {
        if (!res.authSetting['scope.userLocation']) {
          that.showRemind();
        }
      },

      complete: function (res) { },
    })
  },
  showRemind: function(){
    var that = this
    wx.showModal({
      title: '温馨提醒',
      content: '需要获取您的地理位置才能使用小程序',
      showCancel: false,
      confirmText: '获取位置',
      success: function (res) {
        if (res.confirm) {
          that.getAuthor();
        }
      }, fail: (res) => {
        that.getAuthor();
      }
    })
  },
  myGetLocation: function(){
    var that = this
    wx.getLocation({
      success: function (res) {
        app.globalData.longitude = res.longitude
        app.globalData.longitude = res.latitude
        console.log(res.longitude)
        console.log(res.latitude)
        
      },
      fail: function () {
        wx.getSetting({
          success(res) {
            if (!res.authSetting['scope.userLocation']) {
              that.myOpenSetting();
            }
          }
        })
      }
    })
  },
  radioChange: function (e) {
    var identity = e.detail.value
    console.log(identity)
    if(e.detail.value=="学生"){
      this.setData({
        nameShow:'',
        idShow:''
      })
    }
    else{
      this.setData({
        nameShow: '',
        idShow: 'none'
      })
    }
    this.setData({
      identity: identity
    })
  },
  getName:function(e){
    var name = e.detail.value;
    console.log(name);
    this.setData({
      name:name
    })
  },
  getID:function(e){
    var id = e.detail.value
    console.log(id)
    this.setData({
      studentID:id
    })
  }
})