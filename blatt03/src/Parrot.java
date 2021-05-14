
/**
 * A Parrot is a kind of bird. They are among the most intelligent birds.
 * Some have the ability to imitate human voices.
 * 
 * @author Kevin Trebing
 */

public class Parrot extends Bird{
	public String ability = "Talk";
	
	public String allAbilities(){
		return super.allAbilities() + " "+ ability;
	}

}
