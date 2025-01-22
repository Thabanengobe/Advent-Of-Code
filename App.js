const file = require('fs');

const fileData = file.readFileSync('./reports.txt', 'utf8');
const reportList = fileData.split("\n");
let safe = 0;


reportList.forEach(reportData =>{

  const report = reportData.split(' ');

  const isDecrease = Number.parseInt(report[0]) > Number.parseInt(report[1]);
  const isIncrease = Number.parseInt(report[0]) < Number.parseInt(report[1]);

  let isReportSafe = true;

  for(let i = 1; i < report.length; i++){

    const num1 = Number.parseInt(report[(i-1)]);
    const num2 = Number.parseInt(report[i]);

    if(isIncrease && isReportSafe){

      if((isReportInvalid(num1, num2, (num1 < num2)))){
        isReportSafe = false;
      }
    }
    else if( isDecrease && isReportSafe){

      if(isReportInvalid(num2, num1, (num1 > num2))){
        isReportSafe = false;
      }
    }
   }

  if(isReportSafe === true) safe += 1;

})


function isReportInvalid(num1, num2, reportPattern){
  const numPatternDiff = num1 - num2;
  return !( num1 !== num2 && reportPattern && numPatternDiff < 3);
}

