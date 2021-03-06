/**
 * $Id$ 
 *
 * $LastChangedDate$ 
 * 
 * $LastChangedBy$
 */
package instance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;


public class Instance implements Iterable<Attribute>
{
    private static Logger log = Logger.getLogger (Instance.class.getName());
    Classification __classification;
    ArrayList<Attribute> __attrs;


    private Instance () {};
    public Instance (ArrayList<Attribute> attrs, Classification c)
    {
        __attrs = attrs;
        __classification = c;
    }


    public Classification getClassification ()
    {
        return __classification;
    }


    public Attribute getAttribute (Object key)
    {
        for (int i = 0; i < __attrs.size(); i++)
        {
            if ( __attrs.get(i).getKey().equals (key))  return __attrs.get(i);
        }
        
        return null;
    }


    public void removeAttribute (Object key)
    {
        for (int i = 0; i < __attrs.size(); i++)
        {
            if ( __attrs.get(i).getKey().equals (key)) __attrs.remove(i);
        }
    }

    
    public int getAttributeCount ()
    {
        return __attrs.size();
    }


    public Iterator<Attribute> iterator ()
    {
        return __attrs.iterator ();
    }


    public boolean equals (Object obj)
    {
        return true;
    }

    
    /**
     * Return string that uniquely identifies this instance
     */
    public String hashString ()
    {
        StringBuffer sb = new StringBuffer ();
        for ( Attribute attr : __attrs )
        {
            sb.append (String.format("[%s=%s]", attr.getKey(), attr.getValue()));
        }
        return sb.toString();
    }

    
    public String toString ()
    {
        StringBuffer sb = new StringBuffer();
        for ( Attribute attr : __attrs )
        {
            sb.append (String.format("[%s]", attr));
        }
        
        sb.append (String.format ("[class=%s]", __classification == null ? "" : __classification.toString()));
        return sb.toString();
    }
}
