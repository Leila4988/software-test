package cn.cic;

public class Decide {
	String lessThanZero = "输入的金钱数额小于0";
	String moreThanTotal = "输入的金钱数额超出钱币总量";
	String success = "输入数额能由现有钱币拼凑";
	String fail = "输入数额能不由现有钱币拼凑";
	int coins[] = {50, 20, 5, 1};
	 int count[] = {1, 1, 2, 3};
	 int sum = 50+20+5*2+3*1;
	 public String decide(int amount) {
	  if(amount > sum) return moreThanTotal;
	  if(amount < 0) return lessThanZero;
	  //用i种面值的纸币的凑j的方法有n种
	  int[][] dp = new int[coins.length+1][amount+1];
	  for (int i = 0; i < coins.length; i++) dp[i][0] = 1;
	  for (int i = 0; i <= amount; i++) {
	   if ((i%coins[0] == 0) && (i/coins[0] <= count[0])) {//只用第一种钱币就可以表示，并且数量够用
	    dp[1][i] = 1;
	   } else {
	    dp[1][i] = 0;
	   }
	  }
	  for (int i = 2; i <= coins.length; i++) {//每种硬币
	   for (int j = 1; j <= amount; j++) {//每种金额
	    for(int k = 0; k <= count[i-1]; k++) {
	    	if (k*coins[i-1] > j) break;
	        dp[i][j] += dp[i-1][j-k*coins[i-1]];
	       }
	       
	      }
	     }
	     
	     if (dp[coins.length][amount] > 0) {
	      return success;
	     } else {
	      return fail;
	     }
	    }
	    
}
