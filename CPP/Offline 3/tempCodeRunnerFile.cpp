for(int i=0;i<m;i++){
            for(int j=0;j<p;j++){
                ans[i][j] = 0;
                for(int k=0;k<n;k++){
                    ans[i][j] += (*this)[i][k]*A[k][j];
                }
            }
        }