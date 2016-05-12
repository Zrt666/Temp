
public class Problem0_1_knapsack {

	public static void main(String[] args) {

		int w[]={50,30,45,25,5};
		int p[]={200,180,225,200,50};

		
		Problem0_1_knapsack pk=new Problem0_1_knapsack();
		System.out.println("最大价值为："+pk.knapsack_dynamic(w, p, 5, 100));

	}
	
	int knapsack_dynamic(int w[],int p[],int n,int m){
		int i,j,k;
		int v=0;
		int optp[][]=new int[n+1][m+1];
		for(i=0;i<=n;i++){
			optp[i][0]=0;

		}
		
		for(j=0;j<=m;j++){
			optp[0][j]=0;
		}
		
		//计算前i个物体的装入载重物j后的价值optp[i][j]
		for(i=1;i<=n;i++){
			for(j=1;j<=m;j++){
				
				if((j>=w[i-1])&&(optp[i-1][j-w[i-1]]+p[i-1]>optp[i-1][j]))
					optp[i][j]=optp[i-1][j-w[i-1]]+p[i-1];
				else
					optp[i][j]=optp[i-1][j];
			}
		}
		
		//递归装入背包的物体
		j=m;
		for(i=n;i>0;i--){
			if(optp[i][j]>optp[i-1][j]){

				j=j-w[i-1];
				v=p[i-1]+v;
				System.out.println("第"+(i)+"个物品");
			}
		}
		

		
		return v;
		
	}

}
