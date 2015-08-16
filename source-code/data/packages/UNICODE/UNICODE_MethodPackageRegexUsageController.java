package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_MethodPackageRegexUsageController
{
    //attributes
    private String regexUsageAnswer = null;

    public UNICODE_MethodPackageRegexUsageController ( String _regexUsageAnswer )
    {
        regexUsageAnswer = _regexUsageAnswer;
    }
    
    //methods
        //accessors
        public String getRegexUsageAnswer ( )
        {
            return regexUsageAnswer;
        }
        //mutators
        public void setRegexUsageAnswer ( String value )
        {
            regexUsageAnswer = value;
        }

}
