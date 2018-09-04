// pages/voteResult/voteResult.js
const app = getApp();
var sort = require('../../utils/sort.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: null,
    voteId: null,
    content: null,
    endTime: null,
    studentChoose: -1,
    choices: [],
    chooseRate: [],
    choiceLetter: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    self.setData({
      title: options.title,
      voteId: options.voteId,
      endTime: options.endTime.substring(0, 19),
    })
    wx.request({
      url: 'http://127.0.0.1:8080/getVoteResult',
      data: {
        voteId: self.data.voteId,
        student_groupId: app.globalData.openGId,
        studentId: app.globalData.openId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        console.log(res.data);
        let contentData = res.data[0][0];
        let choicesData = [];
        let total = 0;
        for (var i = 1; i < (res.data.length - 1); i++) {
          total += parseInt(res.data[i][2]);
          let choiceArray = { cId: res.data[i][0], cContent: res.data[i][1], cNum: res.data[i][2]};
          choicesData.push(choiceArray);
        }
        let rate = [];
        for (var i = 1; i < (res.data.length - 1); i++) {
          let oneRate = parseInt(res.data[i][2])/total;
          rate.push(oneRate);
        }
        var choice_sort = sort.onlySortChoice(choicesData);
        var choose = res.data[res.data.length-1][0];
        self.setData({
          choices: choice_sort,
          studentChoose: choose,
          content: contentData,
          chooseRate: rate
        })
        console.log(self.data);
      },
      fail: function (error) {
      }
    })
  },
  
})