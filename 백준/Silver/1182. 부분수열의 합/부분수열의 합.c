int i, N, S, a[22], ans;
void b(int n, int s){
    if(n == N-1){
        ans += (s == S-a[N]) +  (s == S);
        return;
    }
    b(n+1, s+a[n+1]);
    b(n+1, s);
}
int main(){
    scanf("%d%d", &N, &S);
    for(i = 1; i <= N ; i++)
        scanf("%d", &a[i]);
    b(0, 0);
    if(!S) --ans;
    printf("%d", ans);
}