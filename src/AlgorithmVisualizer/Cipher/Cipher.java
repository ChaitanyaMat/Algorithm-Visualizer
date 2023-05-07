package AlgorithmVisualizer.Cipher;

public class Cipher {

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        this.index = -1;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        setIndex();
    }

    public String getCode() {
        return code;
    }

    public void setCode(int cipher) {
        this.code="";
        String s1="";
        switch (cipher){
            case 0:for(int i=0;i<msg.length();i++) {
                    char c=msg.charAt(i);
                    int c1=c-5;
                    if(Character.isLetter(c)) {
                        c1=(c1<65)?c1+26:c1;
                        c1=(c1>90)?c1-26:c1;
                        this.code=this.code+(char)(c1);
                    }
                    else if(Character.isDigit(c)){
                        c1=(c1<48)?c1+10:c1;
                        c1=(c1>57)?c1-10:c1;
                        this.code=this.code+(char)(c1);
                    }
                    else
                        this.code=this.code+c;
                }
                break;
            case 1:for(int i=0;i<msg.length();i++){
                    char c=msg.charAt(i);
                    if(Character.isLetter(c))
                        c=(char)(155-c);
                    else if(Character.isDigit(c))
                        c=(char)(105-c);
                    this.code=this.code+c;
                }
                break;
            case 2:s1 = msg+" ";
                for(int i=0;i<s1.length()-1;i++)
                {
                    char c=s1.charAt(i);
                    if(Character.isLetter(c))
                        this.code=this.code+(c-64)+(Character.isLetter(s1.charAt(i+1))?"-":"");
                    else
                        this.code=this.code+c;
                }
                matchLength();
                break;
            case 3:int f=0;
                s1 = msg+" ";
                for(int i=1;i<s1.length();i++){
                    String word="";
                    char delimiter = s1.charAt(i);
                    if(!Character.isLetterOrDigit(delimiter)) {
                        word=s1.substring(f,i);
                        f=i+1;
                        for(int j=0;j<word.length();j++) {
                            char c = word.charAt(j);
                            if (c=='A' || c=='E' || c=='I' || c=='O' || c=='U') {
                                word = word.substring(j) + word.substring(0,j) + ((j==0)?"Y":"");
                                break;
                            }
                        }
                        word += word.length()==0?"":"AY";
                        this.code += word+delimiter;
                    }
                }
                this.code = this.code.trim();
                matchLength();
                break;

        }
    }
    
    private String msg,code;
    private int index;
    public Cipher(String msg,int cipher){
        setMsg(msg);
        setCode(cipher);
    }
    
    public void code(){
        index++;
        if(index==msg.length())
            return;
        if(msg.charAt(index)>code.charAt(index))
            msg=msg.substring(0,index)+(char)(msg.charAt(index)-1)+msg.substring(index+1);
        else if(msg.charAt(index)<code.charAt(index))
            msg=msg.substring(0,index)+(char)(msg.charAt(index)+1)+msg.substring(index+1);
        if(msg.charAt(index)!=code.charAt(index))
            index--;
    }

    private void matchLength() {
        String m = getMsg(), c = getCode();
        while(m.length() < c.length())
            m=m+"X";
        setMsg(m);
    }
}