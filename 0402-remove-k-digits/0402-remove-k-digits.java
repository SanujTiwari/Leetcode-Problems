class Solution {
    public String removeKdigits(String num, int k) {
        int len=num.length();
        if(k==len) return "0";
        char[] stack=new char[len];
        int top=0;
        for(int i=0;i<len;i++){
            char c=num.charAt(i);
            while(top>0 && k>0 && stack[top-1]>c){
                top--;
                k--;
            }
            stack[top++]=c;
        }
        top-=k;
        int idx=0;
        while(idx<top && stack[idx]=='0'){
            idx++;
        }
        return idx==top ? "0" : new String(stack,idx,top-idx);
    }
}