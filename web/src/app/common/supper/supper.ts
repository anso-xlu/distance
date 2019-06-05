export const matching = (matcher = {}, data) => {
  let match = matcher;
  let func;
  // 匹配函数
  while (!func) {
    for (const key in match) {
      if (match[key]) {
        const value = match[key];
        // 从chatData获取matcher的key值
        let content = value[data[key]];
        if (content === undefined) {
          content = value[data.data[key]];
        }

        if (content) {
          // 找到函数返回
          if (typeof content === 'function') {
            func = content;
            break;
          } else {
            // 内容不是函数继续匹配
            match = content;
          }
        } else {
          // 找不到选择default
          func = value.default;
          // 空函数退出while循环
          if (func === undefined) {
            func = () => {
            };
          }
          break;
        }
      } else {
        return () => {
        };
      }
    }
  }
  return func;
};


export const dateFormat = (fmt = 'yyyy-MM-dd HH:mm:ss', date = new Date()) => {
  date = new Date(date);
  const o = {
    'M+': date.getMonth() + 1,                 // 月份
    'd+': date.getDate(),                    // 日
    'H+': date.getHours(),                   // 小时
    'm+': date.getMinutes(),                 // 分
    's+': date.getSeconds(),                 // 秒
    'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
    S: date.getMilliseconds()             // 毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  for (const k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
    }
  }
  return fmt;
};
console.log(dateFormat());
