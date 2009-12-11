/* 
 * WinnerCheck - 
 * 	Retrieve the name of the winner of a game of SC.
 *
 * This code makes liberal use of lysergik's excellent SC Replay Library
 *
 */

import net.xelnaga.screplay.interfaces.IReplay;
import net.xelnaga.screplay.interfaces.IPlayer;
import net.xelnaga.screplay.implementations.Replay;
import net.xelnaga.screplay.implementations.Player;
import net.xelnaga.screplay.loader.ReplayLoader;
import java.io.File;

public class WinnerCheck 
{
	private static IReplay rep;
	private static IPlayer winner;

	private static void announce(File replayfile)
	{
		try 
		{
			rep = ReplayLoader.loadReplay(replayfile);
			winner = rep.getWinner();

			if (winner != null)
			{
				System.out.printf("\nThe Winner is: %s\n\n", winner.getName());
			}
			else { System.out.println("\nThis replay was saved by a loser.\n"); }
		}
		catch (java.io.FileNotFoundException e)
		{
			System.out.printf("File %s not found.\n", replayfile);
		}
		catch (java.io.IOException e)
		{
			System.out.println("Bad stuff happened to IO.");
		}
		catch (net.xelnaga.screplay.unpacker.UnpackException e)
		{
			System.out.printf("Unable to unpack file %s\n", replayfile);
		}
		catch(Exception e)
		{
			System.out.printf("Something went horribly wrong in announce()... %s\n", e);
		}
	}

	private static void usage()
	{
		System.out.println("Usage: WinnerCheck <replayfile>");
	}

	public static void main(String[] args)
	{
		try 
		{
			String filepath = args[0];

			if (filepath != null)
			{
				File replayfile = new File(filepath);
				announce(replayfile);
			}
		}
		catch(java.lang.UnsupportedOperationException e)
		{
			System.out.printf("\nSorry, this file contains an unsupported action: \n%s\n\n", e.getMessage());
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e)
		{
			usage();
		}
		catch(Exception e)
		{
			System.out.printf("Something went horribly wrong in main()... %s\n", e);
		}
	}
}
